(ns clj-europmc.core
  (:require [clj-http.client :as client]
            [clojure.data.xml :as xml]))

(def pmc-rest-uri "http://www.ebi.ac.uk/europepmc/webservices/rest/")

(def search-uri (str pmc-rest-uri "search/query="))

(def profile-uri (str pmc-rest-uri "profile/query="))


(defn search 
  "Searches the query in Europe PMC database and returns a map."
  ([query]
    (let [uri (apply str search-uri query "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))
 ([query resulttype]
    (let [uri (apply str search-uri query "&resulttype=" resulttype "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

  ([query resulttype page]
    (let [uri (apply str search-uri query "&resulttype=" resulttype "&page=" (str page) "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

  ([query resulttype page synonym ]
    (let [uri (apply str search-uri query "&resulttype=" resulttype "&synonym=" (str synonym) "&page=" (str page) "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body))))



(defn profile
  ([query]
    (let [uri (apply str profile-uri query "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))
 
([query resulttype]
    (let [uri (apply str profile-uri query "&resulttype=" resulttype "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

  ([query resulttype page]
    (let [uri (apply str profile-uri query "&resulttype=" resulttype "&page=" (str page) "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

  ([query resulttype page synonym ]
    (let [uri (apply str profile-uri query "&resulttype=" resulttype "&synonym=" (str synonym) "&page=" (str page) "&format=json")
          search-result (client/get uri {:as :json})]
    (-> search-result :body))))


(defn citations 
 [src ext-id page]
    (let [uri (apply str pmc-rest-uri src "/" ext-id "/citations/" page "/json/")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

(defn references 
 [src ext-id page]
    (let [uri (apply str pmc-rest-uri src "/" ext-id "/references/" page "/json/")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

(defn database-links 
 [src ext-id database page]
    (let [uri (apply str pmc-rest-uri src "/" ext-id "/databaseLinks/" "/" database "/" page "/json/")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

(defn labs-links 
 [src ext-id provider-id page]
    (let [uri (apply str pmc-rest-uri src "/" ext-id "/labsLinks/" "/" provider-id "/" page "/json/")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

(defn text-mined-terms 
 [src ext-id semantic-type page]
    (let [uri (apply str pmc-rest-uri src "/" ext-id "/textMinedTerms/" "/" semantic-type "/" page "/json/")
          search-result (client/get uri {:as :json})]
    (-> search-result :body)))

(defn full-text-xml
 [ext-id]
    (let [uri (apply str pmc-rest-uri ext-id "/fullTextXML" )
          search-result (client/get uri {:as :auto})]
    (xml/parse-str (:body search-result))))










