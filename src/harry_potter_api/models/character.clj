(ns harry-potter-api.models.character
  (:require [schema.core :as s]
            [schema-tools.core :as st])
  (import [org.bson.types ObjectId]))

(s/defschema HarryPotterCharacter
  {:_id org.bson.types.ObjectId
   :name s/Str})

(s/defschema NewHarryPotterCharacter
  (st/dissoc HarryPotterCharacter :_id))

(s/defschema OptionalHarryPotterCharacter
  (st/optional-keys NewHarryPotterCharacter))
