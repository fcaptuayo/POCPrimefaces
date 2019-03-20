package com.conexia.converter;

import com.conexia.model.TableRestaurant;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "selectTablesToEntityConverter")
public class SelectTablesToEntityConverter implements Converter {

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
            s = ((TableRestaurant) value).getLocation().toString();
        }
        return s;
    }

    private TableRestaurant getSelectedItemAsEntity(UIComponent comp, String value) {
        TableRestaurant item = null;

        List<TableRestaurant> selectItems = null;
        for (UIComponent uic : comp.getChildren()) {
            if (uic instanceof UISelectItems) {
                selectItems = (List<TableRestaurant>) ((UISelectItems) uic).getValue();

                if (value != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<TableRestaurant> predicate = i -> i.getLocation().equals(value);
                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
                }
            }
        }

        return item;
    }
}
