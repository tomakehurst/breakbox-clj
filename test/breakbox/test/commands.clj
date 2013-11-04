(ns breakbox.test.commands
  (:use midje.sweet)
  (:use breakbox.commands))

(fact "fault of type NETWORK_FAILURE results in an iptables rule"
  (command-for :add
    {
        :name "isolate-web-server",
        :type "NETWORK_FAILURE",
        :direction "IN",
        :from "ALL",
        :to-port 80,
        :protocol "TCP"
    }
  ) => "iptables -A INPUT -p TCP -j DROP --dport 80"
  (command-for :add
    {
      :name "web-server-down",
      :type "SERVICE_FAILURE",
      :direction "IN",
      :from "ALL",
      :to-port 8080,
      :protocol "TCP"
      }
  ) => "iptables -A INPUT -p TCP -j REJECT --dport 8080"
  (command-for :add
    {
      :name "connectivity-to-dependency-down",
      :type "NETWORK_FAILURE",
      :direction "OUT",
      :to "my.dest.host.com",
      :to-port 443,
      :protocol "TCP"
      }
    ) => "iptables -A OUTPUT -p TCP -j DROP --dport 443 -d my.dest.host.com"
)