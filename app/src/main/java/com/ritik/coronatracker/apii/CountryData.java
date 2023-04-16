package com.ritik.coronatracker.apii;

public class CountryData {

        public String updated;
        public String country;
        public String cases;
        public String todayCases;
        public String deaths;
        public String todayDeaths;
        public String recovered;
        public String todayRecovered;
        public String active;
        public String tests;

        public CountryData(String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String tests) {
            this.updated = updated;
            this.country = country;
            this.cases = cases;
            this.todayCases = todayCases;
            this.deaths = deaths;
            this.todayDeaths = todayDeaths;
            this.recovered = recovered;
            this.todayRecovered = todayRecovered;
            this.active = active;
            this.tests = tests;
        }


}


