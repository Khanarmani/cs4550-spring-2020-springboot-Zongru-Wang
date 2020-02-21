package com.example.myapp.services;

import com.example.myapp.models.Widget;

import java.util.*;
import java.util.stream.Collectors;

public class WidgetService {
    Map<String, List<Widget>> widgetList = new HashMap<>();



    public Widget createWidget(Widget widget) {
        if (widgetList.containsKey(widget.getTopicId())) {
            widget.setOrder(widgetList.get(widget.getTopicId()).size());
            widgetList.get(widget.getTopicId()).add(widget);
        } else {
            List<Widget> widgetList1 = new ArrayList<>();
            widget.setOrder(0);
            widgetList1.add(widget);
            widgetList.put(widget.getTopicId(), widgetList1);
        }
        return widget;
    }

    public Widget findWidgetById(String wid) {
        for (List<Widget> widgetList : widgetList.values()) {
            for(int i = 0; i < widgetList.size(); i += 1)  {
                if(widgetList.get(i).getId().equals(wid)) {
                    return widgetList.get(i);
                }
            }
        }
        return null;
    }

    public Map<String, List<Widget>> findAllWidgets() {
        return widgetList;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        if (widgetList.containsKey(topicId)) {
            for (int i = 0; i < widgetList.get(topicId).size(); i += 1) {
                widgetList.get(topicId).get(i).setOrder(i);
            }
            return widgetList.get(topicId);
        } else {
            return new ArrayList<>();
        }
    }

    public int deleteWidget(String wid) {
        for (List<Widget> widgetList : widgetList.values()) {
            for(int i = 0; i < widgetList.size(); i += 1)  {
                if(widgetList.get(i).getId().equals(wid)) {
                    widgetList.remove(i);
                    return 0;
                }
            }
        }
        return 1;
    }

    public int updateWidget(String wid, Widget updatedWidget) {
        for (List<Widget> widgetList : widgetList.values()) {
            for(int i = 0; i < widgetList.size(); i += 1)  {
                if(widgetList.get(i).getId().equals(wid)) {
                    widgetList.set(i, updatedWidget);
                    return 1;
                }
            }
        }
        return 0;
    }

    public List<Widget> updateWidgetUp(Widget widget) {

        String tid = widget.getTopicId();
        // avoid the error
        if (!widgetList.containsKey(tid)) {
            return new ArrayList<>();
        }

        int order = widget.getOrder();

        // if it can keep moving up, then move it
        if (order > 0) {
            // move uo the selected one
            widgetList.get(tid).get(order).setOrder(order - 1);
            // keep the one up to the selected one stay
            widgetList.get(tid).get(order - 1).setOrder(order);
            Collections.swap(widgetList.get(tid), order, order-1);
        }
        return widgetList.get(tid);
    }

    public List<Widget> updateWidgetDown(Widget widget) {
        String tid = widget.getTopicId();
        if (!widgetList.containsKey(tid)) {
            return new ArrayList<>();
        }
        int order = widget.getOrder();


        if (order < widgetList.get(tid).size() - 1) {
            // Get the selected widget down
            widgetList.get(tid).get(order).setOrder(order + 1);
            // Keep the next of the selected stay
            widgetList.get(tid).get(order + 1).setOrder(order);
            Collections.swap(widgetList.get(tid), order, order+1);
        }
        return widgetList.get(tid);
    }


}