(ns breakbox.commands
  (:require [clojure.string :as string]))

(def actions { :add "-A", :delete "-D" })
(def directions { "IN" "INPUT", "OUT" "OUTPUT" })
(def fault-types { "NETWORK_FAILURE" "DROP", "SERVICE_FAILURE", "REJECT" })

(defn command-for [action fault-details]
  (->> ["iptables"
        (actions action)
        (directions (fault-details :direction))
        "-p" (fault-details :protocol)
        "-j" (fault-types (fault-details :type))
        "--dport" (fault-details :to-port)
        (dest-if-present fault-details)]
  (string/join " ")
  (string/trim)))

(defn dest-if-present [fault-details]
  (cond
    (fault-details :to) (str "-d " (fault-details :to))
    :else ""))

