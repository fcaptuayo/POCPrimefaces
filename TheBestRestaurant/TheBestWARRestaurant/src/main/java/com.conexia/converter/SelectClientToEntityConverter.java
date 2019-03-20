package com.conexia.converter;

import com.conexia.model.Client;


import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "selectClientToEntityConverter")
public class SelectClientToEntityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        Object o = null;
        if (!(value == null || value.isEmpty())) {
            o = this.getSelectedItemAsEntity(comp, value);
        }
        return o;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
        String s = "";
        if (value != null) {
            s = ((Client) value).getName().toString();
        }
        return s;
    }

    private Client getSelectedItemAsEntity(UIComponent comp, String value) {
        Client item = null;

        List<Client> selectItems = null;
        for (UIComponent uic : comp.getChildren()) {
            if (uic instanceof UISelectItems) {
                selectItems = (List<Client>) ((UISelectItems) uic).getValue();

                if (value != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<Client> predicate = i -> i.getName().equals(value);
                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
                }
            }
        }

        return item;
    }
}
