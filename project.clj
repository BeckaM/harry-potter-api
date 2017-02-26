(defproject harry-potter-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 ;; API
                 [metosin/compojure-api "1.1.8"]
                 [ring-cors "0.1.9"]
                 ;; Database
                 [com.novemberain/monger "3.1.0"]
                 ;; Auth
                 [buddy/buddy-auth "1.3.0"]
                 [buddy/buddy-hashers "1.0.0"]
                 ;; Misc
                 [clj-time "0.12.2"]
                 [environ "1.1.0"]
                 [cheshire "5.6.3"]]
  :ring {:handler harry-potter-api.handler/app-with-middleware}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.10.0"]]}})
