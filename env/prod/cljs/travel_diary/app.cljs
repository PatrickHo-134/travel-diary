(ns travel-diary.app
  (:require [travel-diary.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
