package com.example.myapp.services;

import com.example.myapp.models.Widget;

import java.util.*;
import java.util.stream.Collectors;

import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    public int deleteWidget(Integer widgetId) {
        widgetRepository.deleteById(widgetId);
        return 1;
    }

    public Widget createWidget(Integer topicId, Widget widget) {
        widget.setTopic(topicRepository.findTopicById(topicId));
        widget.setSize(1);
        List<Widget> widgetList = this.findWidgetsForTopic(topicId);


        if (widgetList.size() <= 0) {
            widget.setwidgetOrder(0);
        } else {
            widget.setwidgetOrder(widgetList.size());
        }
        return widgetRepository.save(widget);
    }

    public int updateWidget(int widgetId, Widget updatedWidget) {
        widgetRepository.updateWidget(updatedWidget, widgetId);
        return 1;
    }

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }

    public List<Widget> findWidgetsForTopic(int topicId) {
        return widgetRepository.findWidgetsForTopic(topicId);
    }

    public Widget findWidgetById(int wid) {
        return widgetRepository.findWidgetById(wid);
//        return widgetRepository.findById(wid).get();
    }


    public List<Widget> updateWidgetUp(Widget widget) {
        int topicId = widget.getTopic().getId();
        int order = widget.getwidgetOrder();

        List<Widget> widgetsForTopic = widgetRepository.findWidgetsForTopic(topicId);

        if (widgetsForTopic.size() > 0 && order > 0) {
            for (int i = 0; i <= widgetsForTopic.size(); i += 1) {
                if (i == order - 1) {
                    Widget newWidget = widgetsForTopic.get(order - 1);
                    widgetRepository.updateWidgetOrder(newWidget.getId(), order);
                    widgetRepository.updateWidgetOrder(widget.getId(), order - 1);
                }
            }
        }
        return widgetRepository.findWidgetsForTopic(topicId);
    }

    public List<Widget> updateWidgetDown(Widget widget) {
        int topicId = widget.getTopic().getId();
        int order = widget.getwidgetOrder();
        List<Widget> widgetList = widgetRepository.findWidgetsForTopic(topicId);
        // if there are widgets in this topic and the widget is not the lowest
        if (widgetList.size() > 0 && order < widgetList.size() - 1) {
            for (int i = 0; i <= widgetList.size(); i += 1) {
                if (i == order) {
                    Widget newWidget = widgetList.get(order + 1);
                    widgetRepository.updateWidgetOrder(newWidget.getId(), order);
                    widgetRepository.updateWidgetOrder(widget.getId(), order + 1);
                }
            }
        }
        return widgetRepository.findWidgetsForTopic(topicId);
    }
}