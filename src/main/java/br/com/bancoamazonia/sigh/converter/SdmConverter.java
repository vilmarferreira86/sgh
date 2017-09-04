package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Sdm;
import br.com.bancoamazonia.sigh.data.SdmDao;

@FacesConverter(value = "sdmConverter", forClass=Sdm.class)
public class SdmConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		System.out.println("Value------->" + value);
		try {
			if (value == null && !"".equals(value)) {
				return null;
			}
			SdmDao sDao = new SdmDao();

			return sDao.getById(Long.valueOf(value));
		} catch (NumberFormatException e) {
			System.out.println("erro------->" + e.getMessage());
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try {
			Sdm s = (Sdm) value;
			return s.getId_sdm().toString();
		} catch (Exception e) {
			return "erro: " + e;
		}
	}

}
