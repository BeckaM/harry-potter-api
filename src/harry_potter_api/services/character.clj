(ns harry-potter-api.services.character
  (require [monger.collection :as mc]
           [harry-potter-api.database :refer :all]
           [monger.operators :refer :all])
  (import [org.bson.types ObjectId]))

(def character-collection "characters")

(defn get-all-characters [key]
  (map #(dissoc % :key)
       (mc/find-maps db character-collection {:key key})))

(defn get-character-by-id [id key]
  (let [character (mc/find-one-as-map db character-collection {:key key
                                                               :_id (ObjectId. id)})]
    (dissoc character :key)))

(defn create-character [new-character key]
  (let [character (merge new-character {:key key})
        new-character (mc/insert-and-return db character-collection character)]
    (dissoc new-character :key)))

(defn update-character [id new-character key]
  (let [character (merge new-character {:key key})]
    (mc/update db character-collection {:key key :_id (ObjectId. id)} {$set character})
    (get-character-by-id id key)))

(defn delete-character [id key]
  (when-let [exists? (get-character-by-id id key)]
    (mc/remove db character-collection {:key key :_id (ObjectId. id)})))
