package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Homologacao;
import br.com.bancoamazonia.sigh.data.HomologacaoDao;

@FacesConverter(value = "convHomologacao", forClass = Homologacao.class)
public class HomologacaoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		System.out.println("Value------->" + value);
		try {
			if (value == null && !"".equals(value)) {
				return null;
			}
			HomologacaoDao hDao = new HomologacaoDao();

			return hDao.getById(Long.valueOf(value));
		} catch (NumberFormatException e) {
			System.out.println("erro------->" + e.getMessage());
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value == null) {
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try {
			Homologacao hml = (Homologacao) value;
			return hml.getIdHomologacao().toString();
		} catch (Exception e) {
			return "erro: " + e;
		}
	}
}
