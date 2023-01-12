"use strict";(self.webpackChunkmicrosite=self.webpackChunkmicrosite||[]).push([[108],{3905:(e,t,r)=>{r.d(t,{Zo:()=>p,kt:()=>f});var n=r(7294);function i(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function o(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function a(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?o(Object(r),!0).forEach((function(t){i(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):o(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function c(e,t){if(null==e)return{};var r,n,i=function(e,t){if(null==e)return{};var r,n,i={},o=Object.keys(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||(i[r]=e[r]);return i}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(i[r]=e[r])}return i}var l=n.createContext({}),s=function(e){var t=n.useContext(l),r=t;return e&&(r="function"==typeof e?e(t):a(a({},t),e)),r},p=function(e){var t=s(e.components);return n.createElement(l.Provider,{value:t},e.children)},d="mdxType",h={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},u=n.forwardRef((function(e,t){var r=e.components,i=e.mdxType,o=e.originalType,l=e.parentName,p=c(e,["components","mdxType","originalType","parentName"]),d=s(r),u=i,f=d["".concat(l,".").concat(u)]||d[u]||h[u]||o;return r?n.createElement(f,a(a({ref:t},p),{},{components:r})):n.createElement(f,a({ref:t},p))}));function f(e,t){var r=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var o=r.length,a=new Array(o);a[0]=u;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c[d]="string"==typeof e?e:i,a[1]=c;for(var s=2;s<o;s++)a[s]=r[s];return n.createElement.apply(null,a)}return n.createElement.apply(null,r)}u.displayName="MDXCreateElement"},2691:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>a,default:()=>d,frontMatter:()=>o,metadata:()=>c,toc:()=>s});var n=r(7462),i=(r(7294),r(3905));const o={sidebar_position:1},a="Architecture",c={unversionedId:"reference/architecture",id:"reference/architecture",title:"Architecture",description:"We illustrate the architecture of an DAML application that integrates with the Bifrost node.",source:"@site/docs/reference/architecture.md",sourceDirName:"reference",slug:"/reference/architecture",permalink:"/bifrost-daml-broker/docs/reference/architecture",draft:!1,tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",previous:{title:"Reference",permalink:"/bifrost-daml-broker/docs/category/reference"},next:{title:"Command Line Reference",permalink:"/bifrost-daml-broker/docs/reference/runtime"}},l={},s=[{value:"Overview",id:"overview",level:2},{value:"Description of the Components",id:"description-of-the-components",level:2},{value:"react-ui",id:"react-ui",level:3},{value:"json-api",id:"json-api",level:3},{value:"daml-participant-node",id:"daml-participant-node",level:3},{value:"trigger-service",id:"trigger-service",level:3},{value:"bifrost-node",id:"bifrost-node",level:3},{value:"daml-bifrost-broker",id:"daml-bifrost-broker",level:3}],p={toc:s};function d(e){let{components:t,...o}=e;return(0,i.kt)("wrapper",(0,n.Z)({},p,o,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"architecture"},"Architecture"),(0,i.kt)("p",null,"We illustrate the architecture of an DAML application that integrates with the Bifrost node."),(0,i.kt)("p",null,(0,i.kt)("img",{alt:"Architecture Diagram",src:r(6905).Z,width:"1130",height:"422"})),(0,i.kt)("h2",{id:"overview"},"Overview"),(0,i.kt)("p",null,"This is an Internal Block Diagram representing the different components of a DAML application integrated with the Bifrost node through the bifrost-daml-broker. Each block with a solid border represents a component that needs to be provided by the Dapp. The block with a dashed border represents components that are already a part of the ecosystem and that just need to be configured and deployed to make the app work. The small blocks attached to the components are called ports and represent a particular interaction of the component with the outside."),(0,i.kt)("h2",{id:"description-of-the-components"},"Description of the Components"),(0,i.kt)("h3",{id:"react-ui"},"react-ui"),(0,i.kt)("p",null,"The react-ui is the UI of the application. The application provides this UI, which interacts with the JSON API usually through the ",(0,i.kt)("inlineCode",{parentName:"p"},"@react/ledger")," Typescript library."),(0,i.kt)("h3",{id:"json-api"},"json-api"),(0,i.kt)("p",null,"The JSON API is a component provided by DAML. It is basically an executable JAR file that acts as an intermediary between a JSON API and the more complete Ledger gRPC API of the DAML Node. Applications can expose it through an nginx reverse proxy."),(0,i.kt)("h3",{id:"daml-participant-node"},"daml-participant-node"),(0,i.kt)("p",null,"The daml-participant-node is the DAML engine that executes and manages the contracts written in DAML."),(0,i.kt)("h3",{id:"trigger-service"},"trigger-service"),(0,i.kt)("p",null,"The trigger-service is another DAML component that allows to react to modifications in the DAML ledger using DAML code."),(0,i.kt)("h3",{id:"bifrost-node"},"bifrost-node"),(0,i.kt)("p",null,"The bifrost-node is the implementation of the Topl blockchain."),(0,i.kt)("h3",{id:"daml-bifrost-broker"},"daml-bifrost-broker"),(0,i.kt)("p",null,"The daml-bifrost-broker is the component that acts as an intermediary between the daml-participant-node and the bifrost-node."))}d.isMDXComponent=!0},6905:(e,t,r)=>{r.d(t,{Z:()=>n});const n=r.p+"assets/images/damlIntegration-5842703444a920f7c30e6d27bf427923.jpg"}}]);