package kz.bitlab.techorda2025.thirdProject.db;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {

    private static List<ApplicationRequest> applicationRequestList = new ArrayList<>();
    private static Long id = 7L;

    static {
        applicationRequestList.add(new ApplicationRequest(1L,"Bagzhan Dinara","Java Standard Edition","А сколько будет длитя сам курс?","+77757843462",true));

        applicationRequestList.add(new ApplicationRequest(2L,"Nurzhan Belebayev","Python Programming Language","Можно как-нибудь получить скидку?","+77476242697",false));

        applicationRequestList.add(new ApplicationRequest(3L,"Zhansaya Batyrbek","Java EE","Как найти работу после курса?","+77777777777",true));

        applicationRequestList.add(new ApplicationRequest(4L,"Madiyar Kypshakbayev","JS","Что надо изучить?","+77471234567",false));

        applicationRequestList.add(new ApplicationRequest(5L,"Farabi Seyilbek","Spring Boot","Этот курс даст мне конкурентное преимущество?","+77477654321",true));

        applicationRequestList.add(new ApplicationRequest(6L,"Asylbek Mahamatkulov","Golang","Этот курс поможет мне стать топовым разработчиком?","+77475555555",true));
    }

    public static List<ApplicationRequest> getApplicationRequestList() {
        return applicationRequestList;
    }

    public static ApplicationRequest getApplicationRequest(Long id) {
        return applicationRequestList.stream().filter(applicationRequest -> applicationRequest.getId()==id).findAny().orElse(null);
    }
    public static void addApplicationRequest(ApplicationRequest applicationRequest) {
        applicationRequest.setId(id);
        applicationRequestList.add(applicationRequest);
        id++;
    }

    public static void saveApplicationRequest(ApplicationRequest applicationRequest) {
        for (int i = 0; i < applicationRequestList.size(); i++) {
            if(applicationRequestList.get(i).getId() == applicationRequest.getId()) {
                applicationRequestList.set(i, applicationRequest);
            }
        }
    }

    public static void deleteApplicationRequest(Long id) {
        applicationRequestList.remove(ApplicationManager.getApplicationRequest(id));
    }
}
