
package com.akexorcist.googledirection.model;

import java.util.ArrayList;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-06-06T16:38-0300")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Direction$$Parcelable
    implements Parcelable, ParcelWrapper<com.akexorcist.googledirection.model.Direction>
{

    private com.akexorcist.googledirection.model.Direction direction$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Direction$$Parcelable.Creator$$3 CREATOR = new Direction$$Parcelable.Creator$$3();

    public Direction$$Parcelable(android.os.Parcel parcel$$0) {
        com.akexorcist.googledirection.model.Direction direction$$2;
        if (parcel$$0 .readInt() == -1) {
            direction$$2 = null;
        } else {
            direction$$2 = readcom_akexorcist_googledirection_model_Direction(parcel$$0);
        }
        direction$$0 = direction$$2;
    }

    public Direction$$Parcelable(com.akexorcist.googledirection.model.Direction direction$$4) {
        direction$$0 = direction$$4;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$1, int flags) {
        if (direction$$0 == null) {
            parcel$$1 .writeInt(-1);
        } else {
            parcel$$1 .writeInt(1);
            writecom_akexorcist_googledirection_model_Direction(direction$$0, parcel$$1, flags);
        }
    }

    private com.akexorcist.googledirection.model.Direction readcom_akexorcist_googledirection_model_Direction(android.os.Parcel parcel$$2) {
        com.akexorcist.googledirection.model.Direction direction$$1;
        direction$$1 = new com.akexorcist.googledirection.model.Direction();
        direction$$1 .errorMessage = parcel$$2 .readString();
        int int$$0 = parcel$$2 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Route> list$$0;
        if (int$$0 < 0) {
            list$$0 = null;
        } else {
            list$$0 = new ArrayList<com.akexorcist.googledirection.model.Route>();
            for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                com.akexorcist.googledirection.model.Route route$$1;
                if (parcel$$2 .readInt() == -1) {
                    route$$1 = null;
                } else {
                    route$$1 = readcom_akexorcist_googledirection_model_Route(parcel$$2);
                }
                list$$0 .add(route$$1);
            }
        }
        direction$$1 .routeList = list$$0;
        direction$$1 .status = parcel$$2 .readString();
        int int$$14 = parcel$$2 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Line> list$$7;
        if (int$$14 < 0) {
            list$$7 = null;
        } else {
            list$$7 = new ArrayList<com.akexorcist.googledirection.model.Line>();
            for (int int$$15 = 0; (int$$15 <int$$14); int$$15 ++) {
                com.akexorcist.googledirection.model.Line line$$2;
                if (parcel$$2 .readInt() == -1) {
                    line$$2 = null;
                } else {
                    line$$2 = readcom_akexorcist_googledirection_model_Line(parcel$$2);
                }
                list$$7 .add(line$$2);
            }
        }
        direction$$1 .line = list$$7;
        int int$$16 = parcel$$2 .readInt();
        ArrayList<com.akexorcist.googledirection.model.GeocodedWaypoint> list$$8;
        if (int$$16 < 0) {
            list$$8 = null;
        } else {
            list$$8 = new ArrayList<com.akexorcist.googledirection.model.GeocodedWaypoint>();
            for (int int$$17 = 0; (int$$17 <int$$16); int$$17 ++) {
                com.akexorcist.googledirection.model.GeocodedWaypoint geocodedWaypoint$$1;
                if (parcel$$2 .readInt() == -1) {
                    geocodedWaypoint$$1 = null;
                } else {
                    geocodedWaypoint$$1 = readcom_akexorcist_googledirection_model_GeocodedWaypoint(parcel$$2);
                }
                list$$8 .add(geocodedWaypoint$$1);
            }
        }
        direction$$1 .geocodedWaypointList = list$$8;
        return direction$$1;
    }

    private com.akexorcist.googledirection.model.Route readcom_akexorcist_googledirection_model_Route(android.os.Parcel parcel$$3) {
        com.akexorcist.googledirection.model.Route route$$0;
        route$$0 = new com.akexorcist.googledirection.model.Route();
        route$$0 .summary = parcel$$3 .readString();
        route$$0 .copyrights = parcel$$3 .readString();
        int int$$2 = parcel$$3 .readInt();
        ArrayList<java.lang.String> list$$1;
        if (int$$2 < 0) {
            list$$1 = null;
        } else {
            list$$1 = new ArrayList<java.lang.String>();
            for (int int$$3 = 0; (int$$3 <int$$2); int$$3 ++) {
                list$$1 .add(parcel$$3 .readString());
            }
        }
        route$$0 .warningList = list$$1;
        com.akexorcist.googledirection.model.Fare fare$$1;
        if (parcel$$3 .readInt() == -1) {
            fare$$1 = null;
        } else {
            fare$$1 = readcom_akexorcist_googledirection_model_Fare(parcel$$3);
        }
        route$$0 .fare = fare$$1;
        int int$$4 = parcel$$3 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Leg> list$$2;
        if (int$$4 < 0) {
            list$$2 = null;
        } else {
            list$$2 = new ArrayList<com.akexorcist.googledirection.model.Leg>();
            for (int int$$5 = 0; (int$$5 <int$$4); int$$5 ++) {
                com.akexorcist.googledirection.model.Leg leg$$1;
                if (parcel$$3 .readInt() == -1) {
                    leg$$1 = null;
                } else {
                    leg$$1 = readcom_akexorcist_googledirection_model_Leg(parcel$$3);
                }
                list$$2 .add(leg$$1);
            }
        }
        route$$0 .legList = list$$2;
        com.akexorcist.googledirection.model.Bound bound$$1;
        if (parcel$$3 .readInt() == -1) {
            bound$$1 = null;
        } else {
            bound$$1 = readcom_akexorcist_googledirection_model_Bound(parcel$$3);
        }
        route$$0 .bound = bound$$1;
        com.akexorcist.googledirection.model.RoutePolyline routePolyline$$2;
        if (parcel$$3 .readInt() == -1) {
            routePolyline$$2 = null;
        } else {
            routePolyline$$2 = readcom_akexorcist_googledirection_model_RoutePolyline(parcel$$3);
        }
        route$$0 .overviewPolyline = routePolyline$$2;
        return route$$0;
    }

    private com.akexorcist.googledirection.model.Fare readcom_akexorcist_googledirection_model_Fare(android.os.Parcel parcel$$4) {
        com.akexorcist.googledirection.model.Fare fare$$0;
        fare$$0 = new com.akexorcist.googledirection.model.Fare();
        fare$$0 .text = parcel$$4 .readString();
        fare$$0 .value = parcel$$4 .readString();
        fare$$0 .currency = parcel$$4 .readString();
        return fare$$0;
    }

    private com.akexorcist.googledirection.model.Leg readcom_akexorcist_googledirection_model_Leg(android.os.Parcel parcel$$5) {
        com.akexorcist.googledirection.model.Leg leg$$0;
        leg$$0 = new com.akexorcist.googledirection.model.Leg();
        leg$$0 .startAddress = parcel$$5 .readString();
        com.akexorcist.googledirection.model.Info info$$1;
        if (parcel$$5 .readInt() == -1) {
            info$$1 = null;
        } else {
            info$$1 = readcom_akexorcist_googledirection_model_Info(parcel$$5);
        }
        leg$$0 .durationInTraffic = info$$1;
        com.akexorcist.googledirection.model.Info info$$2;
        if (parcel$$5 .readInt() == -1) {
            info$$2 = null;
        } else {
            info$$2 = readcom_akexorcist_googledirection_model_Info(parcel$$5);
        }
        leg$$0 .duration = info$$2;
        com.akexorcist.googledirection.model.Info info$$3;
        if (parcel$$5 .readInt() == -1) {
            info$$3 = null;
        } else {
            info$$3 = readcom_akexorcist_googledirection_model_Info(parcel$$5);
        }
        leg$$0 .distance = info$$3;
        int int$$6 = parcel$$5 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Waypoint> list$$3;
        if (int$$6 < 0) {
            list$$3 = null;
        } else {
            list$$3 = new ArrayList<com.akexorcist.googledirection.model.Waypoint>();
            for (int int$$7 = 0; (int$$7 <int$$6); int$$7 ++) {
                com.akexorcist.googledirection.model.Waypoint waypoint$$1;
                if (parcel$$5 .readInt() == -1) {
                    waypoint$$1 = null;
                } else {
                    waypoint$$1 = readcom_akexorcist_googledirection_model_Waypoint(parcel$$5);
                }
                list$$3 .add(waypoint$$1);
            }
        }
        leg$$0 .viaWaypointList = list$$3;
        com.akexorcist.googledirection.model.TimeInfo timeInfo$$1;
        if (parcel$$5 .readInt() == -1) {
            timeInfo$$1 = null;
        } else {
            timeInfo$$1 = readcom_akexorcist_googledirection_model_TimeInfo(parcel$$5);
        }
        leg$$0 .arrivalTime = timeInfo$$1;
        com.akexorcist.googledirection.model.Coordination coordination$$2;
        if (parcel$$5 .readInt() == -1) {
            coordination$$2 = null;
        } else {
            coordination$$2 = readcom_akexorcist_googledirection_model_Coordination(parcel$$5);
        }
        leg$$0 .endLocation = coordination$$2;
        com.akexorcist.googledirection.model.TimeInfo timeInfo$$2;
        if (parcel$$5 .readInt() == -1) {
            timeInfo$$2 = null;
        } else {
            timeInfo$$2 = readcom_akexorcist_googledirection_model_TimeInfo(parcel$$5);
        }
        leg$$0 .departureTime = timeInfo$$2;
        com.akexorcist.googledirection.model.Coordination coordination$$3;
        if (parcel$$5 .readInt() == -1) {
            coordination$$3 = null;
        } else {
            coordination$$3 = readcom_akexorcist_googledirection_model_Coordination(parcel$$5);
        }
        leg$$0 .startLocation = coordination$$3;
        leg$$0 .endAddress = parcel$$5 .readString();
        int int$$8 = parcel$$5 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Step> list$$4;
        if (int$$8 < 0) {
            list$$4 = null;
        } else {
            list$$4 = new ArrayList<com.akexorcist.googledirection.model.Step>();
            for (int int$$9 = 0; (int$$9 <int$$8); int$$9 ++) {
                com.akexorcist.googledirection.model.Step step$$2;
                if (parcel$$5 .readInt() == -1) {
                    step$$2 = null;
                } else {
                    step$$2 = readcom_akexorcist_googledirection_model_Step(parcel$$5);
                }
                list$$4 .add(step$$2);
            }
        }
        leg$$0 .stepList = list$$4;
        return leg$$0;
    }

    private com.akexorcist.googledirection.model.Info readcom_akexorcist_googledirection_model_Info(android.os.Parcel parcel$$6) {
        com.akexorcist.googledirection.model.Info info$$0;
        info$$0 = new com.akexorcist.googledirection.model.Info();
        info$$0 .text = parcel$$6 .readString();
        info$$0 .value = parcel$$6 .readString();
        return info$$0;
    }

    private com.akexorcist.googledirection.model.Waypoint readcom_akexorcist_googledirection_model_Waypoint(android.os.Parcel parcel$$7) {
        com.akexorcist.googledirection.model.Waypoint waypoint$$0;
        waypoint$$0 = new com.akexorcist.googledirection.model.Waypoint();
        waypoint$$0 .index = parcel$$7 .readInt();
        com.akexorcist.googledirection.model.Coordination coordination$$1;
        if (parcel$$7 .readInt() == -1) {
            coordination$$1 = null;
        } else {
            coordination$$1 = readcom_akexorcist_googledirection_model_Coordination(parcel$$7);
        }
        waypoint$$0 .location = coordination$$1;
        waypoint$$0 .interpolation = parcel$$7 .readDouble();
        return waypoint$$0;
    }

    private com.akexorcist.googledirection.model.Coordination readcom_akexorcist_googledirection_model_Coordination(android.os.Parcel parcel$$8) {
        com.akexorcist.googledirection.model.Coordination coordination$$0;
        coordination$$0 = new com.akexorcist.googledirection.model.Coordination();
        coordination$$0 .longitude = parcel$$8 .readDouble();
        coordination$$0 .latitude = parcel$$8 .readDouble();
        return coordination$$0;
    }

    private com.akexorcist.googledirection.model.TimeInfo readcom_akexorcist_googledirection_model_TimeInfo(android.os.Parcel parcel$$9) {
        com.akexorcist.googledirection.model.TimeInfo timeInfo$$0;
        timeInfo$$0 = new com.akexorcist.googledirection.model.TimeInfo();
        timeInfo$$0 .text = parcel$$9 .readString();
        timeInfo$$0 .value = parcel$$9 .readString();
        timeInfo$$0 .timeZone = parcel$$9 .readString();
        return timeInfo$$0;
    }

    private com.akexorcist.googledirection.model.Step readcom_akexorcist_googledirection_model_Step(android.os.Parcel parcel$$10) {
        com.akexorcist.googledirection.model.Step step$$0;
        step$$0 = new com.akexorcist.googledirection.model.Step();
        com.akexorcist.googledirection.model.TransitDetail transitDetail$$1;
        if (parcel$$10 .readInt() == -1) {
            transitDetail$$1 = null;
        } else {
            transitDetail$$1 = readcom_akexorcist_googledirection_model_TransitDetail(parcel$$10);
        }
        step$$0 .transitDetail = transitDetail$$1;
        com.akexorcist.googledirection.model.Info info$$4;
        if (parcel$$10 .readInt() == -1) {
            info$$4 = null;
        } else {
            info$$4 = readcom_akexorcist_googledirection_model_Info(parcel$$10);
        }
        step$$0 .duration = info$$4;
        com.akexorcist.googledirection.model.Info info$$5;
        if (parcel$$10 .readInt() == -1) {
            info$$5 = null;
        } else {
            info$$5 = readcom_akexorcist_googledirection_model_Info(parcel$$10);
        }
        step$$0 .distance = info$$5;
        com.akexorcist.googledirection.model.RoutePolyline routePolyline$$1;
        if (parcel$$10 .readInt() == -1) {
            routePolyline$$1 = null;
        } else {
            routePolyline$$1 = readcom_akexorcist_googledirection_model_RoutePolyline(parcel$$10);
        }
        step$$0 .polyline = routePolyline$$1;
        com.akexorcist.googledirection.model.Coordination coordination$$5;
        if (parcel$$10 .readInt() == -1) {
            coordination$$5 = null;
        } else {
            coordination$$5 = readcom_akexorcist_googledirection_model_Coordination(parcel$$10);
        }
        step$$0 .endLocation = coordination$$5;
        com.akexorcist.googledirection.model.Coordination coordination$$6;
        if (parcel$$10 .readInt() == -1) {
            coordination$$6 = null;
        } else {
            coordination$$6 = readcom_akexorcist_googledirection_model_Coordination(parcel$$10);
        }
        step$$0 .startLocation = coordination$$6;
        step$$0 .htmlInstruction = parcel$$10 .readString();
        step$$0 .maneuver = parcel$$10 .readString();
        step$$0 .travelMode = parcel$$10 .readString();
        int int$$12 = parcel$$10 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Step> list$$6;
        if (int$$12 < 0) {
            list$$6 = null;
        } else {
            list$$6 = new ArrayList<com.akexorcist.googledirection.model.Step>();
            for (int int$$13 = 0; (int$$13 <int$$12); int$$13 ++) {
                com.akexorcist.googledirection.model.Step step$$1;
                if (parcel$$10 .readInt() == -1) {
                    step$$1 = null;
                } else {
                    step$$1 = readcom_akexorcist_googledirection_model_Step(parcel$$10);
                }
                list$$6 .add(step$$1);
            }
        }
        step$$0 .stepList = list$$6;
        return step$$0;
    }

    private com.akexorcist.googledirection.model.TransitDetail readcom_akexorcist_googledirection_model_TransitDetail(android.os.Parcel parcel$$11) {
        com.akexorcist.googledirection.model.TransitDetail transitDetail$$0;
        transitDetail$$0 = new com.akexorcist.googledirection.model.TransitDetail();
        transitDetail$$0 .stopNumber = parcel$$11 .readString();
        com.akexorcist.googledirection.model.TimeInfo timeInfo$$3;
        if (parcel$$11 .readInt() == -1) {
            timeInfo$$3 = null;
        } else {
            timeInfo$$3 = readcom_akexorcist_googledirection_model_TimeInfo(parcel$$11);
        }
        transitDetail$$0 .arrivalTime = timeInfo$$3;
        com.akexorcist.googledirection.model.StopPoint stopPoint$$1;
        if (parcel$$11 .readInt() == -1) {
            stopPoint$$1 = null;
        } else {
            stopPoint$$1 = readcom_akexorcist_googledirection_model_StopPoint(parcel$$11);
        }
        transitDetail$$0 .departureStopPoint = stopPoint$$1;
        transitDetail$$0 .headsign = parcel$$11 .readString();
        com.akexorcist.googledirection.model.TimeInfo timeInfo$$4;
        if (parcel$$11 .readInt() == -1) {
            timeInfo$$4 = null;
        } else {
            timeInfo$$4 = readcom_akexorcist_googledirection_model_TimeInfo(parcel$$11);
        }
        transitDetail$$0 .departureTime = timeInfo$$4;
        com.akexorcist.googledirection.model.Line line$$1;
        if (parcel$$11 .readInt() == -1) {
            line$$1 = null;
        } else {
            line$$1 = readcom_akexorcist_googledirection_model_Line(parcel$$11);
        }
        transitDetail$$0 .line = line$$1;
        com.akexorcist.googledirection.model.StopPoint stopPoint$$2;
        if (parcel$$11 .readInt() == -1) {
            stopPoint$$2 = null;
        } else {
            stopPoint$$2 = readcom_akexorcist_googledirection_model_StopPoint(parcel$$11);
        }
        transitDetail$$0 .arrivalStopPoint = stopPoint$$2;
        return transitDetail$$0;
    }

    private com.akexorcist.googledirection.model.StopPoint readcom_akexorcist_googledirection_model_StopPoint(android.os.Parcel parcel$$12) {
        com.akexorcist.googledirection.model.StopPoint stopPoint$$0;
        stopPoint$$0 = new com.akexorcist.googledirection.model.StopPoint();
        com.akexorcist.googledirection.model.Coordination coordination$$4;
        if (parcel$$12 .readInt() == -1) {
            coordination$$4 = null;
        } else {
            coordination$$4 = readcom_akexorcist_googledirection_model_Coordination(parcel$$12);
        }
        stopPoint$$0 .location = coordination$$4;
        stopPoint$$0 .name = parcel$$12 .readString();
        return stopPoint$$0;
    }

    private com.akexorcist.googledirection.model.Line readcom_akexorcist_googledirection_model_Line(android.os.Parcel parcel$$13) {
        com.akexorcist.googledirection.model.Line line$$0;
        line$$0 = new com.akexorcist.googledirection.model.Line();
        line$$0 .textColor = parcel$$13 .readString();
        line$$0 .color = parcel$$13 .readString();
        int int$$10 = parcel$$13 .readInt();
        ArrayList<com.akexorcist.googledirection.model.Agency> list$$5;
        if (int$$10 < 0) {
            list$$5 = null;
        } else {
            list$$5 = new ArrayList<com.akexorcist.googledirection.model.Agency>();
            for (int int$$11 = 0; (int$$11 <int$$10); int$$11 ++) {
                com.akexorcist.googledirection.model.Agency agency$$1;
                if (parcel$$13 .readInt() == -1) {
                    agency$$1 = null;
                } else {
                    agency$$1 = readcom_akexorcist_googledirection_model_Agency(parcel$$13);
                }
                list$$5 .add(agency$$1);
            }
        }
        line$$0 .agencyList = list$$5;
        com.akexorcist.googledirection.model.Vehicle vehicle$$1;
        if (parcel$$13 .readInt() == -1) {
            vehicle$$1 = null;
        } else {
            vehicle$$1 = readcom_akexorcist_googledirection_model_Vehicle(parcel$$13);
        }
        line$$0 .vehicle = vehicle$$1;
        line$$0 .name = parcel$$13 .readString();
        line$$0 .shortName = parcel$$13 .readString();
        return line$$0;
    }

    private com.akexorcist.googledirection.model.Agency readcom_akexorcist_googledirection_model_Agency(android.os.Parcel parcel$$14) {
        com.akexorcist.googledirection.model.Agency agency$$0;
        agency$$0 = new com.akexorcist.googledirection.model.Agency();
        agency$$0 .name = parcel$$14 .readString();
        agency$$0 .url = parcel$$14 .readString();
        return agency$$0;
    }

    private com.akexorcist.googledirection.model.Vehicle readcom_akexorcist_googledirection_model_Vehicle(android.os.Parcel parcel$$15) {
        com.akexorcist.googledirection.model.Vehicle vehicle$$0;
        vehicle$$0 = new com.akexorcist.googledirection.model.Vehicle();
        vehicle$$0 .name = parcel$$15 .readString();
        vehicle$$0 .iconUrl = parcel$$15 .readString();
        vehicle$$0 .type = parcel$$15 .readString();
        return vehicle$$0;
    }

    private com.akexorcist.googledirection.model.RoutePolyline readcom_akexorcist_googledirection_model_RoutePolyline(android.os.Parcel parcel$$16) {
        com.akexorcist.googledirection.model.RoutePolyline routePolyline$$0;
        routePolyline$$0 = new com.akexorcist.googledirection.model.RoutePolyline();
        routePolyline$$0 .rawPointList = parcel$$16 .readString();
        return routePolyline$$0;
    }

    private com.akexorcist.googledirection.model.Bound readcom_akexorcist_googledirection_model_Bound(android.os.Parcel parcel$$17) {
        com.akexorcist.googledirection.model.Bound bound$$0;
        bound$$0 = new com.akexorcist.googledirection.model.Bound();
        com.akexorcist.googledirection.model.Coordination coordination$$7;
        if (parcel$$17 .readInt() == -1) {
            coordination$$7 = null;
        } else {
            coordination$$7 = readcom_akexorcist_googledirection_model_Coordination(parcel$$17);
        }
        bound$$0 .southwest = coordination$$7;
        com.akexorcist.googledirection.model.Coordination coordination$$8;
        if (parcel$$17 .readInt() == -1) {
            coordination$$8 = null;
        } else {
            coordination$$8 = readcom_akexorcist_googledirection_model_Coordination(parcel$$17);
        }
        bound$$0 .northeast = coordination$$8;
        return bound$$0;
    }

    private com.akexorcist.googledirection.model.GeocodedWaypoint readcom_akexorcist_googledirection_model_GeocodedWaypoint(android.os.Parcel parcel$$18) {
        com.akexorcist.googledirection.model.GeocodedWaypoint geocodedWaypoint$$0;
        geocodedWaypoint$$0 = new com.akexorcist.googledirection.model.GeocodedWaypoint();
        geocodedWaypoint$$0 .placeId = parcel$$18 .readString();
        geocodedWaypoint$$0 .status = parcel$$18 .readString();
        int int$$18 = parcel$$18 .readInt();
        ArrayList<java.lang.String> list$$9;
        if (int$$18 < 0) {
            list$$9 = null;
        } else {
            list$$9 = new ArrayList<java.lang.String>();
            for (int int$$19 = 0; (int$$19 <int$$18); int$$19 ++) {
                list$$9 .add(parcel$$18 .readString());
            }
        }
        geocodedWaypoint$$0 .types = list$$9;
        return geocodedWaypoint$$0;
    }

    private void writecom_akexorcist_googledirection_model_Direction(com.akexorcist.googledirection.model.Direction direction$$3, android.os.Parcel parcel$$19, int flags$$0) {
        parcel$$19 .writeString(direction$$3 .errorMessage);
        if (direction$$3 .routeList == null) {
            parcel$$19 .writeInt(-1);
        } else {
            parcel$$19 .writeInt(direction$$3 .routeList.size());
            for (com.akexorcist.googledirection.model.Route route$$2 : ((java.util.List<com.akexorcist.googledirection.model.Route> ) direction$$3 .routeList)) {
                if (route$$2 == null) {
                    parcel$$19 .writeInt(-1);
                } else {
                    parcel$$19 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Route(route$$2, parcel$$19, flags$$0);
                }
            }
        }
        parcel$$19 .writeString(direction$$3 .status);
        if (direction$$3 .line == null) {
            parcel$$19 .writeInt(-1);
        } else {
            parcel$$19 .writeInt(direction$$3 .line.size());
            for (com.akexorcist.googledirection.model.Line line$$4 : ((java.util.List<com.akexorcist.googledirection.model.Line> ) direction$$3 .line)) {
                if (line$$4 == null) {
                    parcel$$19 .writeInt(-1);
                } else {
                    parcel$$19 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Line(line$$4, parcel$$19, flags$$0);
                }
            }
        }
        if (direction$$3 .geocodedWaypointList == null) {
            parcel$$19 .writeInt(-1);
        } else {
            parcel$$19 .writeInt(direction$$3 .geocodedWaypointList.size());
            for (com.akexorcist.googledirection.model.GeocodedWaypoint geocodedWaypoint$$2 : ((java.util.List<com.akexorcist.googledirection.model.GeocodedWaypoint> ) direction$$3 .geocodedWaypointList)) {
                if (geocodedWaypoint$$2 == null) {
                    parcel$$19 .writeInt(-1);
                } else {
                    parcel$$19 .writeInt(1);
                    writecom_akexorcist_googledirection_model_GeocodedWaypoint(geocodedWaypoint$$2, parcel$$19, flags$$0);
                }
            }
        }
    }

    private void writecom_akexorcist_googledirection_model_Route(com.akexorcist.googledirection.model.Route route$$3, android.os.Parcel parcel$$20, int flags$$1) {
        parcel$$20 .writeString(route$$3 .summary);
        parcel$$20 .writeString(route$$3 .copyrights);
        if (route$$3 .warningList == null) {
            parcel$$20 .writeInt(-1);
        } else {
            parcel$$20 .writeInt(route$$3 .warningList.size());
            for (java.lang.String string$$0 : ((java.util.List<java.lang.String> ) route$$3 .warningList)) {
                parcel$$20 .writeString(string$$0);
            }
        }
        if (route$$3 .fare == null) {
            parcel$$20 .writeInt(-1);
        } else {
            parcel$$20 .writeInt(1);
            writecom_akexorcist_googledirection_model_Fare(route$$3 .fare, parcel$$20, flags$$1);
        }
        if (route$$3 .legList == null) {
            parcel$$20 .writeInt(-1);
        } else {
            parcel$$20 .writeInt(route$$3 .legList.size());
            for (com.akexorcist.googledirection.model.Leg leg$$2 : ((java.util.List<com.akexorcist.googledirection.model.Leg> ) route$$3 .legList)) {
                if (leg$$2 == null) {
                    parcel$$20 .writeInt(-1);
                } else {
                    parcel$$20 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Leg(leg$$2, parcel$$20, flags$$1);
                }
            }
        }
        if (route$$3 .bound == null) {
            parcel$$20 .writeInt(-1);
        } else {
            parcel$$20 .writeInt(1);
            writecom_akexorcist_googledirection_model_Bound(route$$3 .bound, parcel$$20, flags$$1);
        }
        if (route$$3 .overviewPolyline == null) {
            parcel$$20 .writeInt(-1);
        } else {
            parcel$$20 .writeInt(1);
            writecom_akexorcist_googledirection_model_RoutePolyline(route$$3 .overviewPolyline, parcel$$20, flags$$1);
        }
    }

    private void writecom_akexorcist_googledirection_model_Fare(com.akexorcist.googledirection.model.Fare fare$$2, android.os.Parcel parcel$$21, int flags$$2) {
        parcel$$21 .writeString(fare$$2 .text);
        parcel$$21 .writeString(fare$$2 .value);
        parcel$$21 .writeString(fare$$2 .currency);
    }

    private void writecom_akexorcist_googledirection_model_Leg(com.akexorcist.googledirection.model.Leg leg$$3, android.os.Parcel parcel$$22, int flags$$3) {
        parcel$$22 .writeString(leg$$3 .startAddress);
        if (leg$$3 .durationInTraffic == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_Info(leg$$3 .durationInTraffic, parcel$$22, flags$$3);
        }
        if (leg$$3 .duration == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_Info(leg$$3 .duration, parcel$$22, flags$$3);
        }
        if (leg$$3 .distance == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_Info(leg$$3 .distance, parcel$$22, flags$$3);
        }
        if (leg$$3 .viaWaypointList == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(leg$$3 .viaWaypointList.size());
            for (com.akexorcist.googledirection.model.Waypoint waypoint$$2 : ((java.util.List<com.akexorcist.googledirection.model.Waypoint> ) leg$$3 .viaWaypointList)) {
                if (waypoint$$2 == null) {
                    parcel$$22 .writeInt(-1);
                } else {
                    parcel$$22 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Waypoint(waypoint$$2, parcel$$22, flags$$3);
                }
            }
        }
        if (leg$$3 .arrivalTime == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_TimeInfo(leg$$3 .arrivalTime, parcel$$22, flags$$3);
        }
        if (leg$$3 .endLocation == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(leg$$3 .endLocation, parcel$$22, flags$$3);
        }
        if (leg$$3 .departureTime == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_TimeInfo(leg$$3 .departureTime, parcel$$22, flags$$3);
        }
        if (leg$$3 .startLocation == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(leg$$3 .startLocation, parcel$$22, flags$$3);
        }
        parcel$$22 .writeString(leg$$3 .endAddress);
        if (leg$$3 .stepList == null) {
            parcel$$22 .writeInt(-1);
        } else {
            parcel$$22 .writeInt(leg$$3 .stepList.size());
            for (com.akexorcist.googledirection.model.Step step$$3 : ((java.util.List<com.akexorcist.googledirection.model.Step> ) leg$$3 .stepList)) {
                if (step$$3 == null) {
                    parcel$$22 .writeInt(-1);
                } else {
                    parcel$$22 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Step(step$$3, parcel$$22, flags$$3);
                }
            }
        }
    }

    private void writecom_akexorcist_googledirection_model_Info(com.akexorcist.googledirection.model.Info info$$6, android.os.Parcel parcel$$23, int flags$$4) {
        parcel$$23 .writeString(info$$6 .text);
        parcel$$23 .writeString(info$$6 .value);
    }

    private void writecom_akexorcist_googledirection_model_Waypoint(com.akexorcist.googledirection.model.Waypoint waypoint$$3, android.os.Parcel parcel$$24, int flags$$5) {
        parcel$$24 .writeInt(waypoint$$3 .index);
        if (waypoint$$3 .location == null) {
            parcel$$24 .writeInt(-1);
        } else {
            parcel$$24 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(waypoint$$3 .location, parcel$$24, flags$$5);
        }
        parcel$$24 .writeDouble(waypoint$$3 .interpolation);
    }

    private void writecom_akexorcist_googledirection_model_Coordination(com.akexorcist.googledirection.model.Coordination coordination$$9, android.os.Parcel parcel$$25, int flags$$6) {
        parcel$$25 .writeDouble(coordination$$9 .longitude);
        parcel$$25 .writeDouble(coordination$$9 .latitude);
    }

    private void writecom_akexorcist_googledirection_model_TimeInfo(com.akexorcist.googledirection.model.TimeInfo timeInfo$$5, android.os.Parcel parcel$$26, int flags$$7) {
        parcel$$26 .writeString(timeInfo$$5 .text);
        parcel$$26 .writeString(timeInfo$$5 .value);
        parcel$$26 .writeString(timeInfo$$5 .timeZone);
    }

    private void writecom_akexorcist_googledirection_model_Step(com.akexorcist.googledirection.model.Step step$$4, android.os.Parcel parcel$$27, int flags$$8) {
        if (step$$4 .transitDetail == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_TransitDetail(step$$4 .transitDetail, parcel$$27, flags$$8);
        }
        if (step$$4 .duration == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_Info(step$$4 .duration, parcel$$27, flags$$8);
        }
        if (step$$4 .distance == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_Info(step$$4 .distance, parcel$$27, flags$$8);
        }
        if (step$$4 .polyline == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_RoutePolyline(step$$4 .polyline, parcel$$27, flags$$8);
        }
        if (step$$4 .endLocation == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(step$$4 .endLocation, parcel$$27, flags$$8);
        }
        if (step$$4 .startLocation == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(step$$4 .startLocation, parcel$$27, flags$$8);
        }
        parcel$$27 .writeString(step$$4 .htmlInstruction);
        parcel$$27 .writeString(step$$4 .maneuver);
        parcel$$27 .writeString(step$$4 .travelMode);
        if (step$$4 .stepList == null) {
            parcel$$27 .writeInt(-1);
        } else {
            parcel$$27 .writeInt(step$$4 .stepList.size());
            for (com.akexorcist.googledirection.model.Step step$$5 : ((java.util.List<com.akexorcist.googledirection.model.Step> ) step$$4 .stepList)) {
                if (step$$5 == null) {
                    parcel$$27 .writeInt(-1);
                } else {
                    parcel$$27 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Step(step$$5, parcel$$27, flags$$8);
                }
            }
        }
    }

    private void writecom_akexorcist_googledirection_model_TransitDetail(com.akexorcist.googledirection.model.TransitDetail transitDetail$$2, android.os.Parcel parcel$$28, int flags$$9) {
        parcel$$28 .writeString(transitDetail$$2 .stopNumber);
        if (transitDetail$$2 .arrivalTime == null) {
            parcel$$28 .writeInt(-1);
        } else {
            parcel$$28 .writeInt(1);
            writecom_akexorcist_googledirection_model_TimeInfo(transitDetail$$2 .arrivalTime, parcel$$28, flags$$9);
        }
        if (transitDetail$$2 .departureStopPoint == null) {
            parcel$$28 .writeInt(-1);
        } else {
            parcel$$28 .writeInt(1);
            writecom_akexorcist_googledirection_model_StopPoint(transitDetail$$2 .departureStopPoint, parcel$$28, flags$$9);
        }
        parcel$$28 .writeString(transitDetail$$2 .headsign);
        if (transitDetail$$2 .departureTime == null) {
            parcel$$28 .writeInt(-1);
        } else {
            parcel$$28 .writeInt(1);
            writecom_akexorcist_googledirection_model_TimeInfo(transitDetail$$2 .departureTime, parcel$$28, flags$$9);
        }
        if (transitDetail$$2 .line == null) {
            parcel$$28 .writeInt(-1);
        } else {
            parcel$$28 .writeInt(1);
            writecom_akexorcist_googledirection_model_Line(transitDetail$$2 .line, parcel$$28, flags$$9);
        }
        if (transitDetail$$2 .arrivalStopPoint == null) {
            parcel$$28 .writeInt(-1);
        } else {
            parcel$$28 .writeInt(1);
            writecom_akexorcist_googledirection_model_StopPoint(transitDetail$$2 .arrivalStopPoint, parcel$$28, flags$$9);
        }
    }

    private void writecom_akexorcist_googledirection_model_StopPoint(com.akexorcist.googledirection.model.StopPoint stopPoint$$3, android.os.Parcel parcel$$29, int flags$$10) {
        if (stopPoint$$3 .location == null) {
            parcel$$29 .writeInt(-1);
        } else {
            parcel$$29 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(stopPoint$$3 .location, parcel$$29, flags$$10);
        }
        parcel$$29 .writeString(stopPoint$$3 .name);
    }

    private void writecom_akexorcist_googledirection_model_Line(com.akexorcist.googledirection.model.Line line$$3, android.os.Parcel parcel$$30, int flags$$11) {
        parcel$$30 .writeString(line$$3 .textColor);
        parcel$$30 .writeString(line$$3 .color);
        if (line$$3 .agencyList == null) {
            parcel$$30 .writeInt(-1);
        } else {
            parcel$$30 .writeInt(line$$3 .agencyList.size());
            for (com.akexorcist.googledirection.model.Agency agency$$2 : ((java.util.List<com.akexorcist.googledirection.model.Agency> ) line$$3 .agencyList)) {
                if (agency$$2 == null) {
                    parcel$$30 .writeInt(-1);
                } else {
                    parcel$$30 .writeInt(1);
                    writecom_akexorcist_googledirection_model_Agency(agency$$2, parcel$$30, flags$$11);
                }
            }
        }
        if (line$$3 .vehicle == null) {
            parcel$$30 .writeInt(-1);
        } else {
            parcel$$30 .writeInt(1);
            writecom_akexorcist_googledirection_model_Vehicle(line$$3 .vehicle, parcel$$30, flags$$11);
        }
        parcel$$30 .writeString(line$$3 .name);
        parcel$$30 .writeString(line$$3 .shortName);
    }

    private void writecom_akexorcist_googledirection_model_Agency(com.akexorcist.googledirection.model.Agency agency$$3, android.os.Parcel parcel$$31, int flags$$12) {
        parcel$$31 .writeString(agency$$3 .name);
        parcel$$31 .writeString(agency$$3 .url);
    }

    private void writecom_akexorcist_googledirection_model_Vehicle(com.akexorcist.googledirection.model.Vehicle vehicle$$2, android.os.Parcel parcel$$32, int flags$$13) {
        parcel$$32 .writeString(vehicle$$2 .name);
        parcel$$32 .writeString(vehicle$$2 .iconUrl);
        parcel$$32 .writeString(vehicle$$2 .type);
    }

    private void writecom_akexorcist_googledirection_model_RoutePolyline(com.akexorcist.googledirection.model.RoutePolyline routePolyline$$3, android.os.Parcel parcel$$33, int flags$$14) {
        parcel$$33 .writeString(routePolyline$$3 .rawPointList);
    }

    private void writecom_akexorcist_googledirection_model_Bound(com.akexorcist.googledirection.model.Bound bound$$2, android.os.Parcel parcel$$34, int flags$$15) {
        if (bound$$2 .southwest == null) {
            parcel$$34 .writeInt(-1);
        } else {
            parcel$$34 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(bound$$2 .southwest, parcel$$34, flags$$15);
        }
        if (bound$$2 .northeast == null) {
            parcel$$34 .writeInt(-1);
        } else {
            parcel$$34 .writeInt(1);
            writecom_akexorcist_googledirection_model_Coordination(bound$$2 .northeast, parcel$$34, flags$$15);
        }
    }

    private void writecom_akexorcist_googledirection_model_GeocodedWaypoint(com.akexorcist.googledirection.model.GeocodedWaypoint geocodedWaypoint$$3, android.os.Parcel parcel$$35, int flags$$16) {
        parcel$$35 .writeString(geocodedWaypoint$$3 .placeId);
        parcel$$35 .writeString(geocodedWaypoint$$3 .status);
        if (geocodedWaypoint$$3 .types == null) {
            parcel$$35 .writeInt(-1);
        } else {
            parcel$$35 .writeInt(geocodedWaypoint$$3 .types.size());
            for (java.lang.String string$$1 : ((java.util.List<java.lang.String> ) geocodedWaypoint$$3 .types)) {
                parcel$$35 .writeString(string$$1);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.akexorcist.googledirection.model.Direction getParcel() {
        return direction$$0;
    }

    public final static class Creator$$3
        implements Creator<Direction$$Parcelable>
    {


        @Override
        public Direction$$Parcelable createFromParcel(android.os.Parcel parcel$$36) {
            return new Direction$$Parcelable(parcel$$36);
        }

        @Override
        public Direction$$Parcelable[] newArray(int size) {
            return new Direction$$Parcelable[size] ;
        }

    }

}
