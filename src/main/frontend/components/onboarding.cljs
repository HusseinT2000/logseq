(ns frontend.components.onboarding
  (:require [frontend.context.i18n :refer [t]]
            [frontend.handler.route :as route-handler]
            [rum.core :as rum]
            [frontend.ui :as ui]
            [frontend.components.onboarding.setups :as setups]))

(rum/defc intro
  []
  (setups/picker))

(defn help
  []
  [:div.help.cp__sidebar-help-docs
   (let [discord-with-icon [:div.flex-row.inline-flex.items-center
                            [:span.mr-1 (t :help/community)]
                            (ui/icon "brand-discord" {:style {:font-size 20}})]
         list
         [{:title "Usage"
           :children [[[:a
                        {:on-click (fn [] (route-handler/redirect! {:to :shortcut-setting}))}
                        [:div.flex-row.inline-flex.items-center
                         [:span.mr-1 (t :help/shortcuts)]
                         (ui/icon "command" {:style {:font-size 20}})]]]
                      [(t :help/docs) "https://docs.logseq.com/"]
                      ["FAQ" "https://docs.logseq.com/#/page/faq"]]}

          {:title "About"
           :children [[(t :help/start) "https://docs.logseq.com/#/page/getting%20started"]
                      [(t :help/about) "https://logseq.com/blog/about"]]}

          {:title "Development"
           :children [[(t :help/roadmap) "https://trello.com/b/8txSM12G/roadmap"]
                      [(t :help/bug) "https://github.com/logseq/logseq/issues/new?assignees=&labels=&template=bug_report.md&title="]
                      [(t :help/feature) "https://github.com/logseq/logseq/issues/new?assignees=&labels=&template=feature_request.md&title="]
                      [(t :help/changelog) "https://docs.logseq.com/#/page/changelog"]]}

          {:title "Terms"
           :children [[(t :help/privacy) "https://logseq.com/blog/privacy-policy"]
                      [(t :help/terms) "https://logseq.com/blog/terms"]]}

          {:title "Community"
           :children [[(t :help/awesome-logseq) "https://github.com/logseq/awesome-logseq"]
                      [discord-with-icon "https://discord.gg/KpN4eHY"]]}]]

     (map (fn [sublist]
            [[:p.mt-4.mb-1 [:b (:title sublist)]]
             [:ul
              (map (fn [[title href]]
                     [:li
                      (if href
                        [:a {:href href :target "_blank"} title]
                        title)])
                (:children sublist))]])
       list))])
