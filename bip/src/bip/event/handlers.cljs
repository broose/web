(ns bip.event.handlers
  (:require [rum.core :as rum]
            [bip.event.controls :as ectrl]
            [bip.utils :as u]
            ))
(rum/defc audio [path]
  [:audio {:controls true :preload "auto" :auto-play true :src path} ])


(rum/defc at < rum/reactive rum/static [*event present-map]
  (let [event-map (rum/react *event)
        at-hour (:hour event-map)
        at-minute (:minute event-map)
        now-hour (:hour present-map)
        now-minute (:minute present-map)]
    [:div.event.at
     #_(if (u/now? [at-hour at-minute] [now-hour now-minute]) "Sing with me!")
     (str "Fire at " at-hour ":" at-minute )
     (ectrl/edit-mode-button *event)
     (when (:editing event-map)
       (ectrl/at *event))])) 

(rum/defc bip < rum/reactive rum/static [*event present-map]
(let [event-map (rum/react *event)
      at-hour (:hour event-map)
      at-minute (:minute event-map)
      at-second (:second event-map)
      now-hour (:hour present-map)
      now-minute (:minute present-map)
      now-second (:second present-map)]
    [:div.event.bip
     (if (u/now? [at-hour at-minute at-second] [now-hour now-minute now-second]) 
       [:div.happens 
        "Bounce!"
        (audio "sounds/jump.mp3")
        ]
       )
     (str "Fire at " at-hour ":" at-minute)
     (ectrl/edit-mode-button *event)
     (when (:editing event-map)
       (ectrl/at *event))])
  )
