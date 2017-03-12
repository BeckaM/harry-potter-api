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
                    :description "RESTful API example."}
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
            (if-let [character (get-character-by-id id key)]
              (ok character)
              (not-found)))

          (POST "/" request
            :return HarryPotterCharacter
            :query-params [key :- String]
            :body [character NewHarryPotterCharacter]
            :summary "create a character"
            (let [character (create-character character key)]
              (created (str (:uri request) "/" (:_id character)) character)))

          (PUT "/:id" []
            :return HarryPotterCharacter
            :path-params [id :- String]
            :query-params [key :- String]
            :body [character NewHarryPotterCharacter]
            :summary "update a character"
            (if-let [character (update-character id character key)]
              (ok character)
              (not-found)))

          (PATCH "/:id" []
            :return HarryPotterCharacter
            :path-params [id :- String]
            :query-params [key :- String]
            :body [character OptionalHarryPotterCharacter]
            :summary "patch update a character"
            (if-let [character (update-character id character key)]
              (ok character)
              (not-found)))

          (DELETE "/:id" []
            :path-params [id :- String]
            :query-params [key :- String]
            :summary "delete a character"
            (if (delete-character id key)
              (no-content)
              (not-found))))))))

(def app-with-middleware
  (-> app
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])))
