package com.conexia.converter;

import com.conexia.model.Waiter;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "selectWaiterToEntityConverter")
public class SelectWaiterToEntityConverter implements Converter {

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
            s = ((Waiter) value).getName().toString();
        }
        return s;
    }

    private Waiter getSelectedItemAsEntity(UIComponent comp, String value) {
        Waiter item = null;

        List<Waiter> selectItems = null;
        for (UIComponent uic : comp.getChildren()) {
            if (uic instanceof UISelectItems) {
                selectItems = (List<Waiter>) ((UISelectItems) uic).getValue();

                if (value != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<Waiter> predicate = i -> i.getName().equals(value);
                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
                }
            }
        }

        return item;
    }
}
