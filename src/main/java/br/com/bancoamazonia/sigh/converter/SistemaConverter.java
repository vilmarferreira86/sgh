package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Sistema;
import br.com.bancoamazonia.sigh.data.SistemaDao;

@FacesConverter(value = "convSistema", forClass = Sistema.class)
public class SistemaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {

			try {
				SistemaDao sDao = new SistemaDao();
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
			if (value instanceof Sistema && value != null) {
				Sistema sistema = (Sistema) value;
				return sistema.getIdSistema().toString();
			}
		} catch (Exception e) {
			return "erro: " + e;
		}
		return null;
	}

}
