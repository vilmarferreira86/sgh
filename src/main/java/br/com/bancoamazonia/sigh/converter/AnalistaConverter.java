package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Analista;
import br.com.bancoamazonia.sigh.model.Sistema;
import br.com.bancoamazonia.sigh.data.AnalistaDao;

@FacesConverter(value = "convAnalista", forClass = Analista.class)
public class AnalistaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				AnalistaDao anDao = new AnalistaDao();
				if (value.equals("Selecione...")) {
					return null;
				} else {
					return anDao.getById(Long.valueOf(value));
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
			if (value instanceof Analista && value != null) {
				Analista anl = (Analista) value;
				return anl.getIdAnalista().toString();
			}
		} catch (Exception e) {
			return "erro: " + e;
		}
		return null;
	}
}
