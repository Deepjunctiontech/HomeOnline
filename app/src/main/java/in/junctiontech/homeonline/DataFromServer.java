package in.junctiontech.homeonline;

/**
 * Created by lenovo on 28-Jan-16.
 */
public class DataFromServer {




    public void getAppointmentDetailFromJsonString(final String data)
    {

       /* *//*   Property Data*//*
        JSONObject details = data.getJSONObject("details");
        contentValuesAppointment.put("property_type", details.optString("propertyType"));  //  TODO
        contentValuesAppointment.put("bhk_type", details.getString("bhkType"));
        contentValuesAppointment.put("no_of_livingroom", details.getString("numOfLivingRooms"));
        contentValuesAppointment.put("no_of_bedroom", details.getString("numOfBedrooms"));
        contentValuesAppointment.put("no_of_kitchen", details.getString("numofKitchens"));
        contentValuesAppointment.put("no_of_bathroom", details.getString("numOfToilet"));
        contentValuesAppointment.put("no_of_balcony", details.getString("balcony"));
        contentValuesAppointment.put("preferred_visit_time", details.getString("preferredVisitTime"));
        contentValuesAppointment.put("possesion_date", details.getString("passessionTime"));
       */ //contentValuesAppointment.put("status_property_detail", "true");
    }

}
