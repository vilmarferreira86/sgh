package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Sistema;
import br.com.bancoamazonia.sigh.model.Situacao;
import br.com.bancoamazonia.sigh.data.SistemaDao;
import br.com.bancoamazonia.sigh.data.SituacaoDao;

@FacesConverter(value = "convSituacao", forClass = Situacao.class)
public class SituacaoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {

			try {
				SituacaoDao sDao = new SituacaoDao();
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
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		try {
			if (value instanceof Situacao && value != null) {
				Situacao sit = (Situacao) value;
				return sit.getIdSituacao().toString();
			}
		} catch (Exception e) {
			return "erro: " + e;
		}
		return null;
		
	}}
