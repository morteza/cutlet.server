webpackJsonp([0],{176:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}var l=t(11),o=n(l);t(122),t(123),t(42);var a=t(22),r=n(a),i=t(402),u=n(i),s=t(417),c=n(s),d=t(401),f=n(d);t(469),t(458),o.default.module("app",[r.default,"ngMaterial",u.default,c.default]).config(["$locationProvider","$mdIconProvider","$mdThemingProvider",function(e,t,n){"ngInject";e.html5Mode(!0).hashPrefix("!"),n.theme("default")}]).component("app",f.default)},388:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,"",""])},389:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".help-sidebar {\n  color: red;\n}\n",""])},390:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,"",""])},391:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,'.ui.stage.segment {\n  width: 300px;\n  height: 300px;\n  display: inline-block;\n}\n.ball {\n  display: inline-block;\n  width: 100%;\n  height: 100%;\n  border-radius: 100%;\n}\n.ball:before {\n  content: "";\n  position: absolute;\n  top: 1%;\n  left: 5%;\n  width: 90%;\n  height: 90%;\n  border-radius: 100%;\n  -webkit-filter: blur(5px);\n  filter: blur(5px);\n  z-index: 2;\n}\n.ball:after {\n  content: "";\n  position: absolute;\n  display: none;\n  top: 5%;\n  left: 10%;\n  width: 80%;\n  height: 80%;\n  border-radius: 100%;\n  -webkit-filter: blur(1px);\n  filter: blur(1px);\n  z-index: 2;\n  -webkit-transform: rotateZ(-30deg);\n  transform: rotateZ(-30deg);\n}\n.ball .shadow {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  background: radial-gradient(circle, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.1) 40%, rgba(0, 0, 0, 0) 50%);\n  -webkit-transform: rotateX(90deg) translateZ(-160px);\n  transform: rotateX(90deg) translateZ(-160px);\n  z-index: 1;\n}\n.ball.plain {\n  background: black;\n}\n.ball.plain:before,\n.ball.plain:after {\n  display: none;\n}\n.ball.bubble {\n  margin: 0px;\n  background: radial-gradient(circle at 50% 55%, rgba(255, 140, 140, 0.3), rgba(255, 120, 120, 0.4) 40%, rgba(255, 100, 100, 0.5) 60%, rgba(255, 0, 0, 0.6));\n  -webkit-animation: bubble-anim 2s ease-out infinite;\n  animation: bubble-anim 2s ease-out infinite;\n}\n.ball.bubble:before {\n  -webkit-filter: blur(0);\n  filter: blur(0);\n  height: 80%;\n  width: 40%;\n  background: radial-gradient(circle at 130% 130%, rgba(255, 255, 255, 0) 0, rgba(255, 255, 255, 0) 46%, rgba(255, 255, 255, 0.2) 50%, rgba(255, 255, 255, 0.2) 58%, rgba(255, 255, 255, 0) 60%, rgba(255, 255, 255, 0) 100%);\n  -webkit-transform: translateX(131%) translateY(58%) rotateZ(168deg) rotateX(10deg);\n  transform: translateX(131%) translateY(58%) rotateZ(168deg) rotateX(10deg);\n}\n.ball.bubble:after {\n  display: block;\n  background: radial-gradient(circle at 50% 80%, rgba(255, 255, 255, 0), rgba(255, 255, 255, 0) 74%, rgba(255, 255, 255, 0.5) 80%, rgba(255, 255, 255, 0.7) 84%, rgba(255, 255, 255, 0) 100%);\n}\n@-webkit-keyframes bubble-anim {\n  0% {\n    -webkit-transform: scale(1);\n    transform: scale(1);\n  }\n  20% {\n    -webkit-transform: scaleY(0.95) scaleX(1.05);\n    transform: scaleY(0.95) scaleX(1.05);\n  }\n  48% {\n    -webkit-transform: scaleY(1.02) scaleX(0.98);\n    transform: scaleY(1.02) scaleX(0.98);\n  }\n  68% {\n    -webkit-transform: scaleY(0.98) scaleX(1.02);\n    transform: scaleY(0.98) scaleX(1.02);\n  }\n  80% {\n    -webkit-transform: scaleY(1.02) scaleX(0.98);\n    transform: scaleY(1.02) scaleX(0.98);\n  }\n  97%,\n  100% {\n    -webkit-transform: scale(1);\n    transform: scale(1);\n  }\n}\n@keyframes bubble-anim {\n  0% {\n    -webkit-transform: scale(1);\n    transform: scale(1);\n  }\n  20% {\n    -webkit-transform: scaleY(0.95) scaleX(1.05);\n    transform: scaleY(0.95) scaleX(1.05);\n  }\n  48% {\n    -webkit-transform: scaleY(1.02) scaleX(0.98);\n    transform: scaleY(1.02) scaleX(0.98);\n  }\n  68% {\n    -webkit-transform: scaleY(0.98) scaleX(1.02);\n    transform: scaleY(0.98) scaleX(1.02);\n  }\n  80% {\n    -webkit-transform: scaleY(1.02) scaleX(0.98);\n    transform: scaleY(1.02) scaleX(0.98);\n  }\n  97%,\n  100% {\n    -webkit-transform: scale(1);\n    transform: scale(1);\n  }\n}\n',""])},392:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".choice {\n  color: red;\n}\n",""])},393:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".likert {\n  color: red;\n}\nmd-slider[md-discrete] .md-sign,\nmd-slider[md-discrete] .md-sign:after {\n  opacity: 1;\n  -webkit-transform: translate3d(0, 0, 0) scale(1);\n  transform: translate3d(0, 0, 0) scale(1);\n}\n",""])},394:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".longtext {\n  color: red;\n}\n",""])},395:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".statement {\n  color: red;\n}\n",""])},396:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".text {\n  color: red;\n}\n",""])},397:function(e,exports,t){exports=e.exports=t(25)(void 0),exports.push([e.i,".experiment {\n  color: red;\n}\n",""])},401:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(443),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o={template:l.default,restrict:"E"};exports.default=o},402:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(413),r=n(a),i=t(406),u=n(i),s=t(409),c=n(s),d=t(403),f=n(d),m=t(410),p=n(m),b=o.default.module("app.common",[r.default,u.default,c.default]).value("ConfigService",f.default).filter("limitToWords",function(){return p.default.factory}).name;exports.default=b},403:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n={API_BASE:"http://localhost:8080/api/v1",LOGIN_URL:"http://localhost:8080/login",LOGOUT_URL:"http://localhost:8080/logout",SIGNUP_URL:"http://localhost:8080/signup",showHelpSidenav:!1};exports.default=n},404:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(444),o=n(l),a=t(405),r=n(a);t(459);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},405:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(){(0,l.default)(this,e),this.name="footer"};exports.default=o},406:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(404),r=n(a),i=o.default.module("footer",[]).component("footer",r.default).name;exports.default=i},407:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(445),o=n(l),a=t(408),r=n(a);t(460);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},408:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(16),o=n(l),a=t(61),r=n(a),i=function(){function e(t,n,l,a,r,i){"ngInject";(0,o.default)(this,e),console.log("Sidenav controller..."),this.$state=n,this.CmsService=r,this.api=r.api,this.$mdSidenav=l,this.ConfigService=i,this.$transitions=t,t.onSuccess({},this.reloadHelpDocuments())}return e.$inject=["$transitions","$state","$mdSidenav","AuthService","CmsService","ConfigService"],(0,r.default)(e,[{key:"reloadHelpDocuments",value:function(){var e=this;return function(){iri=e.$state.current.name,e.documents=e.CmsService.query({type:"INLINE_HELP",iri:iri})}}},{key:"close",value:function(){this.ConfigService.showHelpSidenav=!1,this.$mdSidenav("help-sidenav").close().then(function(){console.log("Help sidebar is closed.")})}},{key:"open",value:function(){this.ConfigService.showHelpSidenav=!0,this.$mdSidenav("help-sidenav").open().then(function(){console.log("Help sidebar is opened.")})}}]),e}();exports.default=i},409:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(181),r=n(a),i=t(22),u=n(i),s=t(407),c=n(s),d=o.default.module("help",[r.default,u.default]).component("help",c.default).name;exports.default=d},410:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(16),o=n(l),a=t(61),r=n(a),i=function(){function e(){(0,o.default)(this,e)}return(0,r.default)(e,null,[{key:"factory",value:function(e,t){if(isNaN(t))return e;if(t<=0)return"";if(e){var n=e.split(/\s+/);n.length>t&&(e=n.slice(0,t).join(" ")+"…")}return e?String(e).replace(/<[^>]+>/gm,""):""}}]),e}();i.factory.$inject=["input","words"],exports.default=i},411:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(446),o=n(l),a=t(412),r=n(a);t(461);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},412:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(16),o=n(l),a=t(61),r=n(a),i=function(){function e(t,n,l,a){"ngInject";(0,o.default)(this,e),this.name="navbar",this.$state=n,this.$mdDialog=t,this.AuthService=a,this.ConfigService=l}return e.$inject=["$mdDialog","$state","ConfigService","AuthService"],(0,r.default)(e,[{key:"login",value:function(){console.log("nexting...")}},{key:"logout",value:function(){console.log("Logging out..."),this.AuthService.cleanCredentials(),this.$state.go("home")}},{key:"showHelpSidenav",value:function(){this.ConfigService.showHelpSidenav=!0}},{key:"showSignupModal",value:function(e){console.log("Hello signup in scope"),this.$mdDialog.show({contentElement:"#signupModal",parent:angular.element(document.body),targetEvent:e,clickOutsideToClose:!0})}}]),e}();exports.default=i},413:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(22),r=n(a),i=t(411),u=n(i),s=o.default.module("navbar",[r.default]).component("navbar",u.default).name;exports.default=s},414:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(447),o=n(l),a=t(415),r=n(a);t(484);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},415:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(){(0,l.default)(this,e),this.name="about"};exports.default=o},416:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(22),r=n(a),i=t(414),u=n(i),s=o.default.module("about",[r.default]).config(["$stateProvider","$urlRouterProvider",function(e,t){"ngInject";t.otherwise("/"),e.state("about",{url:"/about",component:"about"})}]).component("about",u.default).name;exports.default=s},417:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(441),r=n(a),i=t(416),u=n(i),s=t(438),c=n(s),d=o.default.module("app.components",[r.default,c.default,u.default]).name;exports.default=d},418:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(448),o=n(l),a=t(419),r=n(a);t(462);var i={restrict:"E",require:{experiment:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},419:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(16),o=n(l),a=t(61),r=n(a),i=function(){function e(t){"ngInject";(0,o.default)(this,e),this.initialSize=10,this.size=0,this.nextReward=0,this.totalRewards=0,this.pumps=0}return e.$inject=["$scope"],(0,r.default)(e,[{key:"$onInit",value:function(){this.experiment.hideNextButton()}},{key:"inflate",value:function(){this.pumps++,this.size++;var e=this.nextReward+.05;if(Math.random()>.8)return this.totalRewards=this.nextReward+this.totalReward-.05,this.nextReward=.05,this.pumps=1,void(this.size=initialSize);this.nextReward=e}}]),e}();exports.default=i},420:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(418),u=n(i),s=o.default.module("bart",[r.default,"ngMaterial"]).component("bart",u.default).name;exports.default=s},421:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(449),o=n(l),a=t(422),r=n(a);t(463);var i={restrict:"E",require:{api:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},422:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(t){"ngInject";(0,l.default)(this,e)};o.$inject=["$scope"],exports.default=o},423:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(421),u=n(i),s=o.default.module("choice",[r.default,"ngMaterial"]).component("choice",u.default).name;exports.default=s},424:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(450),o=n(l),a=t(425),r=n(a);t(464);var i={restrict:"E",require:{api:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},425:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(t){"ngInject";(0,l.default)(this,e)};o.$inject=["$scope"],exports.default=o},426:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(424),u=n(i),s=o.default.module("likert",[r.default,"ngMaterial"]).component("likert",u.default).name;exports.default=s},427:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(451),o=n(l),a=t(428),r=n(a);t(465);var i={restrict:"E",require:{api:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},428:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(t){"ngInject";(0,l.default)(this,e)};o.$inject=["$scope"],exports.default=o},429:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(427),u=n(i),s=o.default.module("longtext",[r.default,"ngMaterial"]).component("longtext",u.default).name;exports.default=s},430:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(452),o=n(l),a=t(431),r=n(a);t(466);var i={restrict:"E",require:{api:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},431:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(t){"ngInject";(0,l.default)(this,e)};o.$inject=["$scope"],exports.default=o},432:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(430),u=n(i),s=o.default.module("statement",[r.default,"ngMaterial"]).component("statement",u.default).name;exports.default=s},433:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(453),o=n(l),a=t(434),r=n(a);t(467);var i={restrict:"E",require:{api:"^experiment"},bindings:{element:"=",answer:"="},template:o.default,controller:r.default};exports.default=i},434:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(t){"ngInject";(0,l.default)(this,e)};o.$inject=["$scope"],exports.default=o},435:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l);t(42);var a=t(22),r=n(a),i=t(433),u=n(i),s=o.default.module("text",[r.default,"ngMaterial"]).component("text",u.default).name;exports.default=s},436:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(454),o=n(l),a=t(437),r=n(a);t(468);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},437:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(16),o=n(l),a=t(61),r=n(a),i=function(){function e(t){"ngInject";(0,o.default)(this,e),this.answer={},this.showNextButton=!0;var n=this;this.elements=t.get("/assets/elements.json").then(function(e){n.elements=e.data.elements,n.current=n.elements[0],n.current.index=0,n.answer.index=0,n.answer.id=n.current.id})}return e.$inject=["$http"],(0,r.default)(e,[{key:"next",value:function(){if(this.showNextButton=!0,console.log(this.answer),this.current.index+1>=this.elements.length)return void console.log("finished");var e=this.current.index;this.current=this.elements[++e],this.current.index=e,this.answer.id=this.current.id,this.answer.index=this.current.index,this.answer.value=""}},{key:"hideNextButton",value:function(){this.showNextButton=!1}}]),e}();exports.default=i},438:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(22),r=n(a),i=t(436),u=n(i),s=t(435),c=n(s),d=t(429),f=n(d),m=t(426),p=n(m),b=t(432),v=n(b),g=t(423),h=n(g),_=t(420),w=n(_),x=o.default.module("experiment",[r.default,c.default,f.default,p.default,v.default,h.default,w.default]).component("experiment",u.default).name;exports.default=x},439:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(455),o=n(l),a=t(440),r=n(a);t(485);var i={restrict:"E",bindings:{},template:o.default,controller:r.default};exports.default=i},440:function(e,exports,t){"use strict";Object.defineProperty(exports,"__esModule",{value:!0});var n=t(16),l=function(e){return e&&e.__esModule?e:{default:e}}(n),o=function e(){(0,l.default)(this,e),this.name="home"};exports.default=o},441:function(e,exports,t){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(exports,"__esModule",{value:!0});var l=t(11),o=n(l),a=t(22),r=n(a),i=t(439),u=n(i),s=o.default.module("home",[r.default]).config(["$stateProvider","$urlRouterProvider",function(e,t){"ngInject";t.otherwise("/"),e.state("home",{url:"/",component:"home"})}]).component("home",u.default).name;exports.default=s},443:function(e,exports){e.exports='  <md-toolbar class="md-lime">\n    <div class="md-toolbar-tools">\n      <h2>Cut The Cake</h2>\n    </div>\n  </md-toolbar>\n\n<md-content flex layout-padding>\n  <div ui-view></div>\n</md-content>\n'},444:function(e,exports){e.exports='<md-divider></md-divider>\n<br />\n<div layout="column" layout-align="center center">\n  <span flex="nogrow" class="md-caption">\n    2017 &copy; cut.social\n  </span>\n  <br />\n  <div layout="row" layout-align="center center">\n    <a class="md-button" flex="nogrow" class="md-caption" ui-sref="support">Support</a>\n  </div>\n</div>\n'},445:function(e,exports){e.exports='<md-sidenav class="md-sidenav-left" md-component-id="help-sidenav" md-is-locked-open="$ctrl.ConfigService.showHelpSidenav">\n\n  <section layout="column" style="height:calc(100vh - 64px)">\n\n    <md-toolbar class="md-hue-4">\n      <div class="md-toolbar-tools" layout="row">\n        <h1>Help</h1>\n        <span flex></span>\n\n        <md-button aria-label="close" class="md-icon-button" title="close" ng-click="$ctrl.close()">\n          <md-icon>close</md-icon>\n        </md-button>\n      </div>\n    </md-toolbar>\n    <md-content layout="column" layout-padding flex>\n\n      <md-card flex ng-repeat="document in $ctrl.documents.data">\n        {{document.title}} <br />\n        {{document.content}}\n      </md-card>\n\n    </md-content>\n  </section>\n\n</md-sidenav>\n'},446:function(e,exports){e.exports='<md-toolbar class="md-hue-2" ng-show="!$ctrl.showSearch" ng-cloak ng-if="!$ctrl.$state.includes(\'login\')">\n\n  <div class="md-toolbar-tools">\n    <h2>Cut</h2>\n    <span flex></span>\n\n    <md-button aria-label="search" class="md-icon-button" title="search" ng-click="$ctrl.showSearch = !$ctrl.showSearch">\n      <md-icon class="md-flip">search</md-icon>\n    </md-button>\n\n    <md-button ng-if="$ctrl.AuthService.isAuthenticated()" ui-sref="inbox" aria-label="Messages" class="md-icon-button" title="Messages">\n      <md-icon class="md-flip">inbox</md-icon>\n    </md-button>\n\n    <md-button ng-if="!$ctrl.AuthService.isAuthenticated()" ui-sref="login" aria-label="Login" class="md-icon-button" title="Login">\n      <md-icon class="md-flip">exit_to_app</md-icon>\n    </md-button>\n\n    <md-button ng-if="!$ctrl.AuthService.isAuthenticated()" ng-click="$ctrl.showSignupModal($event)" aria-label="Sign Up" class="md-icon-button" title="Sign Up">\n      <md-icon class="md-flip">person_add</md-icon>\n    </md-button>\n\n    <md-button aria-label="راهنما" ng-disabled="$ctrl.ConfigService.showHelpSidenav" class="md-icon-button" title="Help" ng-click="$ctrl.showHelpSidenav()">\n      <md-icon>help</md-icon>\n    </md-button>\n\n    <md-button ng-if="$ctrl.AuthService.isAuthenticated()" aria-label="Logout" class="md-icon-button" title="Logout" ng-click="$ctrl.logout()">\n      <md-icon class="md-flip">power_settings_new</md-icon>\n    </md-button>\n\n  </div>\n\n</md-toolbar>\n\n<div style="visibility: hidden">\n  <div class="md-dialog-container" id="signupModal">\n    <md-dialog layout-padding>\n      <h2>Sign Up Dialog</h2>\n      <p>\n        Hello Signup!\n      </p>\n    </md-dialog>\n  </div>\n</div>\n\n'},447:function(e,exports){e.exports="About.html\n"},448:function(e,exports){e.exports='<md-content layout-padding class="md-block" layout="column">\n  <p flex>{{ $ctrl.element.content }}</p>\n\n  <div flex layout="row" layout-align="center center" class="ui basic stage segment">\n    <figure class="ball bubble"></figure>\n  </div>\n\n  <md-button flex="nogrow" class="md-primary md-raised" ng-click="$ctrl.experiment.next()">بعدی</md-button>\n\n</md-content>\n'},449:function(e,exports){e.exports='<md-content layout-padding class="md-block">\n  <p>{{ $ctrl.element.content }}</p>\n  <md-input-container class="md-block" ng-if="!$ctrl.element.radio">\n    <label>Answer</label>\n    <md-select ng-model="$ctrl.answer.value">\n      <md-option value="none" ng-if="$ctrl.element.allowNone"><em>None</em></md-option>\n      <md-option ng-repeat="c in $ctrl.element.choices" ng-value="c.value">\n        {{c.title}}\n      </md-option>\n    </md-select>\n  </md-input-container>\n  <md-radio-group class="md-primary" ng-model="$ctrl.answer.value" ng-if="$ctrl.element.radio">\n    <md-radio-button value="none" ng-if="$ctrl.element.allowNone">None</md-radio-button>\n    <md-radio-button value="{{c.value}}" ng-repeat="c in $ctrl.element.choices">{{c.title}}</md-radio-button>\n  </md-radio-group>\n\n</md-content>\n'},450:function(e,exports){e.exports='<md-content layout="column" layout-padding class="md-block">\n  <p flex>{{ $ctrl.element.content }}</p>\n  <md-slider-container flex class="md-block" layout="row">\n      <div flex="10" layout layout-align="center center">\n        <span class="md-body-1">Answer</span>\n      </div>\n      <md-slider flex md-discrete ng-model="$ctrl.answer.value" step="{{$ctrl.element.step}}" min="{{$ctrl.element.min}}" max="{{$ctrl.element.max}}" aria-label="likert">\n      </md-slider>\n  </md-slider-container>\n</md-content>\n'},451:function(e,exports){e.exports='<md-content layout-padding class="md-block">\n  <p>{{ $ctrl.element.content }}</p>\n  <md-input-container class="md-block">\n    <label>Answer</label>\n    <textarea rows="5" ng-model="$ctrl.answer.value" md-maxlength="{{$ctrl.element.maxlength}}" rows="5" md-select-on-focus></textarea>\n  </md-input-container>\n</md-content>\n'},452:function(e,exports){e.exports='<md-content layout-padding class="md-block">\n  <p>{{ $ctrl.element.content }}</p>\n</md-content>\n'},453:function(e,exports){e.exports='<md-content layout-padding class="md-block">\n  <p>{{ $ctrl.element.content }}</p>\n  <md-input-container class="md-block">\n    <label>Answer</label>\n    <input ng-model="$ctrl.answer.value">\n  </md-input-container>\n</md-content>\n'},454:function(e,exports){e.exports='\n<div ng-switch="$ctrl.current.type">\n  <text element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="text"></text>\n  <longtext element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="longtext"></longtext>\n  <likert element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="likert"></likert>\n  <choice element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="choice"></choice>\n  <statement element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="statement"></statement>\n  <bart element="$ctrl.current" answer="$ctrl.answer" ng-switch-when="bart"></bart>\n</div>\n\n<div layout="row" layout-align="end center" ng-if="$ctrl.showNextButton">\n  <md-button class="md-raised md-primary" ng-click="$ctrl.next()">Next</md-button>\n</div>\n'},455:function(e,exports){e.exports="<experiment></experiment>\n"},459:function(e,exports,t){var n=t(388);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},460:function(e,exports,t){var n=t(389);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},461:function(e,exports,t){var n=t(390);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},462:function(e,exports,t){var n=t(391);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},463:function(e,exports,t){var n=t(392);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},464:function(e,exports,t){var n=t(393);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},465:function(e,exports,t){var n=t(394);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},466:function(e,exports,t){var n=t(395);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},467:function(e,exports,t){var n=t(396);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},468:function(e,exports,t){var n=t(397);"string"==typeof n&&(n=[[e.i,n,""]]);t(26)(n,{});n.locals&&(e.exports=n.locals)},484:function(e,exports){},485:function(e,exports){},486:function(e,exports,t){t(175),e.exports=t(176)}},[486]);