(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-363dfc08"],{2423:function(t,e,n){"use strict";n.d(e,"a",(function(){return a}));var i=n("b775");function a(t){return Object(i["a"])({url:"/vue-element-admin/article/pv",method:"get",params:{pv:t}})}},"2cbf":function(t,e,n){"use strict";n("73e0")},"333d":function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[n("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},a=[];n("a9e3");Math.easeInOutQuad=function(t,e,n,i){return t/=i/2,t<1?n/2*t*t+e:(t--,-n/2*(t*(t-2)-1)+e)};var o=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function r(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,n){var i=s(),a=t-i,l=20,u=0;e="undefined"===typeof e?500:e;var c=function t(){u+=l;var s=Math.easeInOutQuad(u,i,a,e);r(s),u<e?o(t):n&&"function"===typeof n&&n()};c()}var u={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},c=u,d=(n("2cbf"),n("2877")),p=Object(d["a"])(c,i,a,!1,null,"6af373ef",null);e["a"]=p.exports},"420d":function(t,e,n){"use strict";n.d(e,"h",(function(){return a})),n.d(e,"f",(function(){return o})),n.d(e,"b",(function(){return r})),n.d(e,"c",(function(){return s})),n.d(e,"e",(function(){return l})),n.d(e,"i",(function(){return u})),n.d(e,"d",(function(){return c})),n.d(e,"g",(function(){return d})),n.d(e,"a",(function(){return p})),n.d(e,"j",(function(){return m}));var i=n("b775");function a(t){return Object(i["a"])({url:"/room/getRole",method:"get",params:t})}function o(t){return Object(i["a"])({url:"/room/recording/stop",method:"post",data:t})}function r(t){return Object(i["a"])({url:"/room/recording/start",method:"post",data:t})}function s(t){return Object(i["a"])({url:"/room/checkPassword?roomId="+t.roomId+"&password="+t.password,method:"post"})}function l(t){return Object(i["a"])({url:"/room/disconnect",method:"post",data:t})}function u(t){return Object(i["a"])({url:"/room/findById?id="+t,method:"get"})}function c(t){return Object(i["a"])({url:"/room/checkRoomPermission?roomId="+t,method:"get"})}function d(){return Object(i["a"])({url:"/room/findPublicRoomPage",method:"get"})}function p(t){return Object(i["a"])({url:"/room/save",method:"post",data:t})}function m(t){return Object(i["a"])({url:"/room/update",method:"post",data:t})}},"4e82":function(t,e,n){"use strict";var i=n("23e7"),a=n("1c0b"),o=n("7b0b"),r=n("d039"),s=n("a640"),l=[],u=l.sort,c=r((function(){l.sort(void 0)})),d=r((function(){l.sort(null)})),p=s("sort"),m=c||!d||!p;i({target:"Array",proto:!0,forced:m},{sort:function(t){return void 0===t?u.call(o(this)):u.call(o(this),a(t))}})},6724:function(t,e,n){"use strict";n("8d41");var i="@@wavesContext";function a(t,e){function n(n){var i=Object.assign({},e.value),a=Object.assign({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),o=a.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var r=o.getBoundingClientRect(),s=o.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":(s=document.createElement("span"),s.className="waves-ripple",s.style.height=s.style.width=Math.max(r.width,r.height)+"px",o.appendChild(s)),a.type){case"center":s.style.top=r.height/2-s.offsetHeight/2+"px",s.style.left=r.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(n.pageY-r.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(n.pageX-r.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=a.color,s.className="waves-ripple z-active",!1}}return t[i]?t[i].removeHandle=n:t[i]={removeHandle:n},n}var o={bind:function(t,e){t.addEventListener("click",a(t,e),!1)},update:function(t,e){t.removeEventListener("click",t[i].removeHandle,!1),t.addEventListener("click",a(t,e),!1)},unbind:function(t){t.removeEventListener("click",t[i].removeHandle,!1),t[i]=null,delete t[i]}},r=function(t){t.directive("waves",o)};window.Vue&&(window.waves=o,Vue.use(r)),o.install=r;e["a"]=o},"73e0":function(t,e,n){},"8d41":function(t,e,n){},c8c5:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("div",{staticClass:"filter-container"},[n("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v(" 添加 ")])],1),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],key:t.tableKey,staticStyle:{width:"100%"},attrs:{data:t.list,border:"",fit:"","highlight-current-row":""},on:{"sort-change":t.sortChange}},[n("el-table-column",{attrs:{label:"序号",type:"index",align:"center",width:"50"}}),n("el-table-column",{attrs:{label:"会议名称",width:"200px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[n("span",[t._v(t._s(i.name))])]}}])}),n("el-table-column",{attrs:{label:"主持人",width:"150px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[n("span",[t._v(t._s(i.hostName))])]}}])}),n("el-table-column",{attrs:{label:"开始时间",width:"200px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[n("span",[t._v(t._s(i.startTime))])]}}])}),n("el-table-column",{attrs:{label:"会议时长",width:"120px",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[n("span",[t._v(t._s(i.duration+"分钟"))])]}}])}),n("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row,a=e.$index;return[n("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.handleUpdate(i)}}},[t._v(" 修改 ")]),"published"!=i.status?n("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(e){return t.joinMeeting(i)}}},[t._v(" 加入 ")]):t._e(),"deleted"!=i.status?n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(e){return t.handleDelete(i,a)}}},[t._v(" 删除 ")]):t._e()]}}])})],1),n("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total > 0"}],attrs:{total:t.total,page:t.listQuery.page,limit:t.listQuery.limit},on:{"update:page":function(e){return t.$set(t.listQuery,"page",e)},"update:limit":function(e){return t.$set(t.listQuery,"limit",e)},pagination:t.getList}}),n("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[n("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{rules:t.rules,model:t.temp,"label-position":"left","label-width":"100px"}},[n("el-form-item",{attrs:{label:"会议名称",prop:"name"}},[n("el-input",{model:{value:t.temp.name,callback:function(e){t.$set(t.temp,"name",e)},expression:"temp.name"}})],1),n("el-form-item",{attrs:{label:"会议描述"}},[n("el-input",{attrs:{autosize:{minRows:2,maxRows:4},type:"textarea",placeholder:"请输入会议描述"},model:{value:t.temp.description,callback:function(e){t.$set(t.temp,"description",e)},expression:"temp.description"}})],1),n("el-form-item",{attrs:{label:"主持人",prop:"host"}},[n("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择主持人"},model:{value:t.temp.hostId,callback:function(e){t.$set(t.temp,"hostId",e)},expression:"temp.hostId"}},t._l(t.userList,(function(t){return n("el-option",{key:t.id,attrs:{label:t.realname,value:t.id}})})),1)],1),n("el-form-item",{attrs:{label:"开始时间",prop:"startTime"}},[n("el-date-picker",{attrs:{type:"datetime",format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"请选择开始时间"},model:{value:t.temp.startTime,callback:function(e){t.$set(t.temp,"startTime",e)},expression:"temp.startTime"}})],1),n("el-form-item",{attrs:{label:"会议时长",prop:"duration"}},[n("el-input",{model:{value:t.temp.duration,callback:function(e){t.$set(t.temp,"duration",e)},expression:"temp.duration"}})],1)],1),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v(" 取消 ")]),n("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updateData()}}},[t._v(" 保存 ")])],1)],1),n("el-dialog",{attrs:{visible:t.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(e){t.dialogPvVisible=e}}},[n("el-table",{staticStyle:{width:"100%"},attrs:{data:t.pvData,border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{prop:"key",label:"Channel"}}),n("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPvVisible=!1}}},[t._v("Confirm")])],1)],1)],1)},a=[],o=(n("d81d"),n("13d5"),n("4e82"),n("a434"),n("d3b7"),n("3ca3"),n("ddb0"),n("420d")),r=n("c24f"),s=n("2423"),l=n("6724"),u=n("ed08"),c=n("333d"),d=[{key:"CN",display_name:"China"},{key:"US",display_name:"USA"},{key:"JP",display_name:"Japan"},{key:"EU",display_name:"Eurozone"}],p=d.reduce((function(t,e){return t[e.key]=e.display_name,t}),{}),m={name:"ComplexTable",components:{Pagination:c["a"]},directives:{waves:l["a"]},filters:{statusFilter:function(t){var e={published:"success",draft:"info",deleted:"danger"};return e[t]},typeFilter:function(t){return p[t]}},data:function(){return{tableKey:0,list:null,total:0,listLoading:!0,listQuery:{page:1,limit:20,name:void 0,description:void 0,duration:void 0,sort:"+id"},importanceOptions:[1,2,3],calendarTypeOptions:d,sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"],showReviewer:!1,temp:{id:void 0,title:"",type:"",temp:""},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"修改",create:"创建"},dialogPvVisible:!1,pvData:[],rules:{name:[{required:!0,message:"会议名称不能为空",trigger:"change"}],hostId:[{required:!0,message:"主持人不能为空",trigger:"change"}],startTime:[{required:!0,message:"请选择会议时间",trigger:"change"}],duration:[{required:!0,message:"title is required",trigger:"blur"}]},downloadLoading:!1,userList:[]}},created:function(){this.getList(),this.getAllUser()},methods:{getList:function(){var t=this;this.listLoading=!0,Object(o["g"])().then((function(e){t.list=e.data.records,t.total=e.data.total,t.listLoading=!1}))},handleFilter:function(){this.listQuery.page=1,this.getList()},handleModifyStatus:function(t,e){this.$message({message:"操作Success",type:"success"}),t.status=e},sortChange:function(t){var e=t.prop,n=t.order;"id"===e&&this.sortByID(n)},sortByID:function(t){this.listQuery.sort="ascending"===t?"+id":"-id",this.handleFilter()},resetTemp:function(){this.temp={id:void 0,importance:1,remark:"",timestamp:new Date,title:"",status:"published",type:""}},handleCreate:function(){var t=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},createData:function(){var t=this;this.$refs["dataForm"].validate((function(e){e&&(t.temp.roomType=0,Object(o["a"])(t.temp).then((function(e){200==e.code&&(t.$message({message:"添加成功",type:"success"}),t.getList(),t.dialogFormVisible=!1)})))}))},handleUpdate:function(t){var e=this;this.temp=Object.assign({},t),this.temp.timestamp=new Date(this.temp.timestamp),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},updateData:function(){var t=this;this.$refs["dataForm"].validate((function(e){if(e){var n=Object.assign({},t.temp);Object(o["j"])(n).then((function(e){200==e.code&&(t.dialogFormVisible=!1,t.getList(),t.$notify({title:"Success",message:"修改成功",type:"success",duration:2e3}))}))}}))},handleDelete:function(t,e){this.$notify({title:"Success",message:"Delete Successfully",type:"success",duration:2e3}),this.list.splice(e,1)},handleFetchPv:function(t){var e=this;Object(s["a"])(t).then((function(t){e.pvData=t.data.pvData,e.dialogPvVisible=!0}))},handleDownload:function(){var t=this;this.downloadLoading=!0,Promise.all([n.e("chunk-5ed393f0"),n.e("chunk-2125b98f")]).then(n.bind(null,"4bf8")).then((function(e){var n=["timestamp","title","type","importance","status"],i=["timestamp","title","type","importance","status"],a=t.formatJson(i);e.export_json_to_excel({header:n,data:a,filename:"table-list"}),t.downloadLoading=!1}))},formatJson:function(t){return this.list.map((function(e){return t.map((function(t){return"timestamp"===t?Object(u["c"])(e[t]):e[t]}))}))},getSortClass:function(t){var e=this.listQuery.sort;return e==="+".concat(t)?"ascending":"descending"},getAllUser:function(){var t=this;Object(r["a"])().then((function(e){t.userList=e.data,console.log(t.userList)}))},joinMeeting:function(t){var e=this.$router.resolve({path:"/room/meeting"});window.open(e.href+"?roomId="+t.id,"_blank")}}},f=m,g=n("2877"),h=Object(g["a"])(f,i,a,!1,null,null,null);e["default"]=h.exports}}]);