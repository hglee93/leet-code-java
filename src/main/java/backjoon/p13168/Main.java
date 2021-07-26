package backjoon.p13168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Map<String, Float> shortestSearch(String startCity,
                                                     Map<String, Map<String, List<Transport>>> transportInfo, boolean isNaeilro) {
        Map<String, Float> newMap = new HashMap<>();
        PriorityQueue<City> pq = new PriorityQueue<City>((City c1, City c2) -> {
            return c1.price >= c2.price ? 1 : -1;
        });

        pq.add(new City(startCity, 0));

        while(!pq.isEmpty()) {

            City visitCity = pq.poll();
            if(newMap.containsKey(visitCity.name)) { continue; }

            newMap.put(visitCity.name, visitCity.price);
            Map<String, List<Transport>> nextMap = transportInfo.getOrDefault(visitCity.name, new HashMap<>());

            for(Map.Entry<String, List<Transport>> entry : nextMap.entrySet()) {
                if(!newMap.containsKey(entry.getKey())) {
                    float min = Float.MAX_VALUE;
                    if(!isNaeilro) {
                        min = Collections.min(entry.getValue(), (t1, t2) -> {
                            return t1.price > t2.price ? 1 : -1;
                        }).price;
                    } else {
                        for(Transport transport : entry.getValue()) {
                            float price = transport.price;
                            if(transport.type.equals("Mugunghwa") || transport.type.equals("ITX-Saemaeul") || transport.type.equals("ITX-Cheongchun")) {
                                price = 0;
                            }
                            else if(transport.type.equals("V-Train") || transport.type.equals("S-Train")) {
                                price = price / 2.0f;
                            }
                            min = Math.min(min, price);
                        }
                    }
                    pq.add(new City(entry.getKey(), visitCity.price + min));
                }
            }
        }

        return newMap;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int naeilro_price = Integer.parseInt(input[1]);

        String[] cityArray = br.readLine().split(" ");
        List<String> cities = Arrays.asList(cityArray);

        int travel_city_count = Integer.parseInt(br.readLine());
        String[] travelArray = br.readLine().split(" ");
        List<String> travelCities = Arrays.asList(travelArray);

        int transport_count = Integer.parseInt(br.readLine());
        Map<String, Map<String, List<Transport>>> transportInfo = new HashMap<>();

        // 1. (출발지, (도착지, 교통수단)) 형태로 데이터 저장
        for(int i = 0; i < transport_count; ++i) {
            String[] transportInputs = br.readLine().split(" ");
            Transport transport = new Transport(transportInputs[0], Integer.parseInt(transportInputs[3]));
            String startCity = transportInputs[1];
            String endCity = transportInputs[2];

            if(transportInfo.containsKey(startCity) && transportInfo.get(startCity).containsKey(endCity)) {
                transportInfo.get(startCity).get(endCity).add(transport);
                transportInfo.get(endCity).get(startCity).add(transport);
            } else {
                List<Transport> transportList = new ArrayList<>();
                transportList.add(transport);
                Map<String, List<Transport>> endMap = transportInfo.getOrDefault(startCity, new HashMap<>());
                endMap.put(endCity, transportList);
                transportInfo.put(startCity, endMap);

                transportList = new ArrayList<>();
                transportList.add(transport);
                endMap = transportInfo.getOrDefault(endCity, new HashMap<>());
                endMap.put(startCity, transportList);
                transportInfo.put(endCity, endMap);
            }
        }

        Map<String, Map<String, Float>> shortestMap = new HashMap<>();
        Map<String, Map<String, Float>> shortestMapNaeilro = new HashMap<>();

        float normalTravelCost = 0;
        float naeilroTravelCost = naeilro_price;

        // 2. 촤단경로 탐색
        for(int i = 0; i < travel_city_count - 1; ++i) {

            String startCity = travelCities.get(i);
            String endCity = travelCities.get(i + 1);

            Map<String, Float> shortestPath;
            Map<String, Float> shortestPathNaeilro;

            if(shortestMap.containsKey(startCity)) {
                shortestPath = shortestMap.get(startCity);
                shortestPathNaeilro = shortestMapNaeilro.get(startCity);
            } else {
                shortestPath = shortestSearch(startCity, transportInfo, false);
                shortestMap.put(startCity, shortestPath);

                shortestPathNaeilro = shortestSearch(startCity, transportInfo, true);
                shortestMapNaeilro.put(startCity, shortestPathNaeilro);
            }
            normalTravelCost += shortestPath.getOrDefault(endCity, 0.0f);
            naeilroTravelCost += shortestPathNaeilro.getOrDefault(endCity, 0.0f);
        }

        if(normalTravelCost > naeilroTravelCost) {
            System.out.println("Yes");
            return;
        }

        System.out.println("No");
        return;
    }
}

class Transport {
    String type;
    float price;

    public Transport(String type, float price) {
        this.type = type;
        this.price = price;
    }
}

class City {
    String name;
    float price;
    public City(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
