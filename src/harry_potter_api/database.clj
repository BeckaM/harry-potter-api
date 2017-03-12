(ns harry-potter-api.database
  (require [monger.core :as mg]
           [monger.json]))

(def db (mg/get-db (mg/connect) "harry-potter-api"))
