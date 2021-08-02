(ns travel-diary.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[travel-diary started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[travel-diary has shut down successfully]=-"))
   :middleware identity})
