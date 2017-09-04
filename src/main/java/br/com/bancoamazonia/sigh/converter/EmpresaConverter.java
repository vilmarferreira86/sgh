package br.com.bancoamazonia.sigh.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bancoamazonia.sigh.model.Empresa;
import br.com.bancoamazonia.sigh.data.EmpresaDao;

@FacesConverter(value = "convEmpresa", forClass = Empresa.class)
public class EmpresaConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value){
		System.out.println("Value------->"+value);
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			EmpresaDao eDao = new EmpresaDao();
			
			return eDao.getById(Long.valueOf(value));
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
			Empresa emp = (Empresa) value;
			return emp.getIdEmpresa().toString();
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}
}
