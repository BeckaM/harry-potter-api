(defproject harry-potter-api "0.0.0"
  :description "Example RESTful API"
  :url "https://github.com/yuhama/harry-potter-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 ;; API
                 [metosin/compojure-api "1.1.8"]
                 [ring-cors "0.1.9"]
                 ;; Database
                 [com.novemberain/monger "3.1.0"]]
  :ring {:handler harry-potter-api.handler/app-with-middleware}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.10.0"]]}})
