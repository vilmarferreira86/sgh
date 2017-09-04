package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Agencia;
import br.com.bancoamazonia.sigh.data.AgenciaDao;

@FacesConverter(value = "convAgencia", forClass = Agencia.class)
public class AgenciaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value){
		System.out.println("Value------->"+value);
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			AgenciaDao agDao = new AgenciaDao();
			
			return agDao.getById(Long.valueOf(value));
		}catch(NumberFormatException e){
			System.out.println("erro------->"+e.getMessage());
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if(value == null){
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try{
			Agencia ag = (Agencia) value;
			return ag.getIdAgencia().toString();
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}
