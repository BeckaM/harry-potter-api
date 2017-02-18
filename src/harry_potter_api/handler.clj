(ns harry-potter-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [ring.middleware.cors :refer [wrap-cors]]))

(def characters
  (atom [{:name "Ginny"}]))

(s/defschema HarryPotterCharacter
  {:name s/Str})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Harry Potter API"
                    :description "Simple API example."}
             :tags [{:name "characters", :description "Harry Potter Characters"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/characters" []
        :return [HarryPotterCharacter]
        :summary "Gets a list of characters"
        (ok @characters))

      (POST "/characters" []
        :return HarryPotterCharacter
        :summary "Creates a new character"
        :body [character HarryPotterCharacter]
        (do
          (swap! characters conj character)
          (ok character))))))

(def app-with-middleware
  (-> app
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])))
