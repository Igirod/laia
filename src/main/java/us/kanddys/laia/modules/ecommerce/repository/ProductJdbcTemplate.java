package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;

@Repository
public class ProductJdbcTemplate {

   @Autowired
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<ProductDTO> findProductByMerchantId(Long idSolicitud) {
      if (verificarCantidadIntervenciones(idSolicitud).equals(0)) {
         return new ArrayList<IntervencionVO>();
      } else {
         Map<String, Object> params = new HashMap<>();
         params.put("idSolicitud", idSolicitud);
         List<IntervencionVO> listVOS = new ArrayList<>();
         listVOS = namedParameterJdbcTemplate.query(sql_select_intervenciones, params,
               (rs, rowNum) -> new IntervencionVO(
                     Long.valueOf(rs.getLong("productId")),
                     Long.valueOf(rs.getLong("idArea")),
                     rs.getString("nombreArea"),
                     rs.getTimestamp("fechaIntervencion").toString(),
                     Long.valueOf(rs.getLong("idTipoIntervencion")),
                     rs.getString("descripcionTipoIntervencion"),
                     rs.getString("descripcion"),
                     rs.getString("usuario"),
                     (Integer.valueOf(rs.getInt("fuente")) != null ? Integer.valueOf(rs.getInt("fuente")) : null),
                     rs.getTimestamp("fechaHora").toString(),
                     (rs.getString("adjuntoUuid") != null ? rs.getString("adjuntoUuid") : null),
                     new ArrayList<SolicitudDocVO>()));
         return obtenerDocumentosSolicitud(
               listVOS.stream().map(IntervencionVO::getIdIntervencion).collect(Collectors.toList()), listVOS);
      }
   }
}
