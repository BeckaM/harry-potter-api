(ns harry-potter-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.swagger.json-schema :as json-schema]
            [harry-potter-api.models.character :refer :all]
            [harry-potter-api.services.character :refer :all]))

(defmethod json-schema/convert-class org.bson.types.ObjectId [e _] {:type "string" :pattern (str e)})

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

      (context "/characters" []
        :tags ["characters"]

        (routes
          (GET "/" []
            :return [HarryPotterCharacter]
            :query-params [key :- String]
            :summary "gets a list of characters"
            (ok (get-all-characters key)))

          (GET "/:id" []
            :return HarryPotterCharacter
            :path-params [id :- String]
            :query-params [key :- String]
            :summary "gets character by id"
            (ok (get-character-by-id id key)))

          (POST "/" []
            :return HarryPotterCharacter
            :query-params [key :- String]
            :body [character NewHarryPotterCharacter]
            :summary "create a character"
            (ok (create-character character key)))

          (PUT "/:id" []
            :return HarryPotterCharacter
            :path-params [id :- String]
            :query-params [key :- String]
            :body [character NewHarryPotterCharacter]
            :summary "update a character"
            (ok (update-character id character key)))

          (PATCH "/:id" []
            :return HarryPotterCharacter
            :path-params [id :- String]
            :query-params [key :- String]
            :body [character OptionalHarryPotterCharacter]
            :summary "patch update a character"
            (ok (update-character id character key))))))))

(def app-with-middleware
  (-> app
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])))
