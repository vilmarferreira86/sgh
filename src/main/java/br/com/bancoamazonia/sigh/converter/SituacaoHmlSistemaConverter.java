package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Situacao_Homologacao_Sistema;
import br.com.bancoamazonia.sigh.data.SituacaoHomologacaoSistemasDao;

@FacesConverter(value = "convSituacaoHmlSistemaConverter", forClass = Situacao_Homologacao_Sistema.class)
public class SituacaoHmlSistemaConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				SituacaoHomologacaoSistemasDao sDao = new SituacaoHomologacaoSistemasDao();
				if (value.equals("Selecione...")) {
					return null;
				} else {
					return sDao.getById(Long.valueOf(value));
				}

			} catch (NumberFormatException e) {
				System.out.println("erro------->" + e.getMessage());
			}
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		try {
			if (value instanceof Situacao_Homologacao_Sistema && value != null) {
				Situacao_Homologacao_Sistema sitHml = (Situacao_Homologacao_Sistema) value;
				return sitHml.getIdSituacaoHomologacaoSistema().toString();
			}
		} catch (Exception e) {
			return "erro: " + e;
		}
		return null;
	}
}
