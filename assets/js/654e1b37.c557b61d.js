"use strict";(self.webpackChunkmicrosite=self.webpackChunkmicrosite||[]).push([[850],{3905:(e,t,a)=>{a.d(t,{Zo:()=>s,kt:()=>v});var l=a(7294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var l=Object.getOwnPropertySymbols(e);t&&(l=l.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,l)}return a}function n(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function i(e,t){if(null==e)return{};var a,l,r=function(e,t){if(null==e)return{};var a,l,r={},o=Object.keys(e);for(l=0;l<o.length;l++)a=o[l],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(l=0;l<o.length;l++)a=o[l],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var u=l.createContext({}),p=function(e){var t=l.useContext(u),a=t;return e&&(a="function"==typeof e?e(t):n(n({},t),e)),a},s=function(e){var t=p(e.components);return l.createElement(u.Provider,{value:t},e.children)},d="mdxType",c={inlineCode:"code",wrapper:function(e){var t=e.children;return l.createElement(l.Fragment,{},t)}},h=l.forwardRef((function(e,t){var a=e.components,r=e.mdxType,o=e.originalType,u=e.parentName,s=i(e,["components","mdxType","originalType","parentName"]),d=p(a),h=r,v=d["".concat(u,".").concat(h)]||d[h]||c[h]||o;return a?l.createElement(v,n(n({ref:t},s),{},{components:a})):l.createElement(v,n({ref:t},s))}));function v(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=a.length,n=new Array(o);n[0]=h;var i={};for(var u in t)hasOwnProperty.call(t,u)&&(i[u]=t[u]);i.originalType=e,i[d]="string"==typeof e?e:r,n[1]=i;for(var p=2;p<o;p++)n[p]=a[p];return l.createElement.apply(null,n)}return l.createElement.apply(null,a)}h.displayName="MDXCreateElement"},1355:(e,t,a)=>{a.r(t),a.d(t,{assets:()=>u,contentTitle:()=>n,default:()=>d,frontMatter:()=>o,metadata:()=>i,toc:()=>p});var l=a(7462),r=(a(7294),a(3905));const o={sidebar_position:2},n="Command Line Reference",i={unversionedId:"reference/runtime",id:"reference/runtime",title:"Command Line Reference",description:"We intruduce the list of parameters available to start the broker.",source:"@site/docs/reference/runtime.md",sourceDirName:"reference",slug:"/reference/runtime",permalink:"/bifrost-daml-broker/docs/reference/runtime",draft:!1,tags:[],version:"current",sidebarPosition:2,frontMatter:{sidebar_position:2},sidebar:"tutorialSidebar",previous:{title:"Architecture",permalink:"/bifrost-daml-broker/docs/reference/architecture"}},u={},p=[{value:"Usage",id:"usage",level:2},{value:"Example",id:"example",level:3},{value:"-n, --topl-network &lt;value&gt;",id:"-n---topl-network-value",level:3},{value:"-u, --topl-uri &lt;value&gt;",id:"-u---topl-uri-value",level:3},{value:"-a, --topl-api-key &lt;value&gt;",id:"-a---topl-api-key-value",level:3},{value:"-h, --daml-host &lt;value&gt;",id:"-h---daml-host-value",level:3},{value:"-p, --daml-port &lt;value&gt;",id:"-p---daml-port-value",level:3},{value:"-s, --daml-security-enabled &lt;value&gt;",id:"-s---daml-security-enabled-value",level:3},{value:"-t, --daml-access-token &lt;value&gt;",id:"-t---daml-access-token-value",level:3},{value:"-t, --daml-application-id &lt;value&gt;",id:"-t---daml-application-id-value",level:3},{value:"-o, --daml-operator-party &lt;value&gt;",id:"-o---daml-operator-party-value",level:3},{value:"-k, --keyfile &lt;value&gt;",id:"-k---keyfile-value",level:3},{value:"-w, --password &lt;value&gt;",id:"-w---password-value",level:3}],s={toc:p};function d(e){let{components:t,...a}=e;return(0,r.kt)("wrapper",(0,l.Z)({},s,a,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("h1",{id:"command-line-reference"},"Command Line Reference"),(0,r.kt)("p",null,"We intruduce the list of parameters available to start the broker."),(0,r.kt)("h2",{id:"usage"},"Usage"),(0,r.kt)("p",null,"We need the following parameters to launch the broker."),(0,r.kt)("h3",{id:"example"},"Example"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-bash"},"bifrost-daml-broker 0.2\nUsage: bifrost-daml-broker [options]\n\n  -n, --topl-network <value>\n                           the Topl network to connect to, one of: main, valhalla, and private\n  -u, --topl-uri <value>   the URI of the Topl network to connect to, for example https://127.0.0.1/\n  -a, --topl-api-key <value>\n                           the API key for the Topl network\n  -h, --daml-host <value>  the host of the ledger, for example localhost\n  -p, --daml-port <value>  the port where the ledger is listening, for example 6865\n  -s, --daml-security-enabled <value>\n                           whether to use TLS for the connection to the ledger\n  -t, --daml-access-token <value>\n                           the access token for the ledger\n  -t, --daml-application-id <value>\n                           the application id for the ledger, for DAML Hub hosted application the right value is 'damlhub', which is the default value when omitted\n  -o, --daml-operator-party <value>\n                           the party that will be used to submit transactions to the ledger\n  -k, --keyfile <value>    the file that contains the operator key, for example keyfile.json\n  -w, --password <value>   the password for the keyfile\n")),(0,r.kt)("h3",{id:"-n---topl-network-value"},"-n, --topl-network <value",">"),(0,r.kt)("p",null,"The Topl network to connect to, potential values are: main, valhalla, and private."),(0,r.kt)("h3",{id:"-u---topl-uri-value"},"-u, --topl-uri <value",">"),(0,r.kt)("p",null,"The URI of the Topl network to connect to, for example ",(0,r.kt)("a",{parentName:"p",href:"https://127.0.0.1/"},"https://127.0.0.1/"),"."),(0,r.kt)("h3",{id:"-a---topl-api-key-value"},"-a, --topl-api-key <value",">"),(0,r.kt)("p",null,"The API key for the Topl network."),(0,r.kt)("h3",{id:"-h---daml-host-value"},"-h, --daml-host <value",">"),(0,r.kt)("p",null,"The host of the ledger, for example localhost."),(0,r.kt)("h3",{id:"-p---daml-port-value"},"-p, --daml-port <value",">"),(0,r.kt)("p",null,"The port where the ledger is listening, for example 6865."),(0,r.kt)("h3",{id:"-s---daml-security-enabled-value"},"-s, --daml-security-enabled <value",">"),(0,r.kt)("p",null,"Whether to use TLS for the connection to the ledger. Possible values are ",(0,r.kt)("inlineCode",{parentName:"p"},"true")," and ",(0,r.kt)("inlineCode",{parentName:"p"},"false"),"."),(0,r.kt)("h3",{id:"-t---daml-access-token-value"},"-t, --daml-access-token <value",">"),(0,r.kt)("p",null,"When the ledger is secured, this is the access token to access the Ledger API."),(0,r.kt)("h3",{id:"-t---daml-application-id-value"},"-t, --daml-application-id <value",">"),(0,r.kt)("p",null,"The application id for the ledger, for DAML Hub hosted application the right value is 'damlhub', which is the default value when this parameter is omitted."),(0,r.kt)("h3",{id:"-o---daml-operator-party-value"},"-o, --daml-operator-party <value",">"),(0,r.kt)("p",null,"The party that will be used to submit and read transactions from the ledger."),(0,r.kt)("h3",{id:"-k---keyfile-value"},"-k, --keyfile <value",">"),(0,r.kt)("p",null,"The file that contains the operator key, for example keyfile.json. This parameter is optional. When we do not include this parameter the broker will not sign transactions as operator."),(0,r.kt)("h3",{id:"-w---password-value"},"-w, --password <value",">"),(0,r.kt)("p",null,"The password for the key file. This is only required when a key file is present."))}d.isMDXComponent=!0}}]);