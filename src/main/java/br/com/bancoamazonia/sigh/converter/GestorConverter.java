package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Gestor;
import br.com.bancoamazonia.sigh.data.GestorDao;

@FacesConverter(value = "convGestor", forClass = Gestor.class)
public class GestorConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				GestorDao gDao = new GestorDao();
				if (value.equals("Selecione...")) {
					return null;
				} else {
					return gDao.getById(Long.valueOf(value));
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
			if (value instanceof Gestor && value != null) {
				Gestor gestor = (Gestor) value;
				return gestor.getIdGestor().toString();
			}
		} catch (Exception e) {
			return "erro: " + e;
		}
		return null;
	}
}
