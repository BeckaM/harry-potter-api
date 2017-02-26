(ns harry-potter-api.database
  (require [environ.core :refer [env]]
           [monger.core :as mg]
           [monger.json]))

(def db (mg/get-db (mg/connect) "harry-potter-api"))
