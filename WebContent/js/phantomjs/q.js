var args = require('system').args;
var postBody = args[1];//'email=belchiorpalma@gmail.com&password=belchior';
var page = require('webpage').create();

//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1280, height: 768 };
page.open('http://localhost:8081/Quest/Login','POST', postBody, function(status) {
  console.log('status: '+status);

 	var page2 = require('webpage').create();
  var postBody = args[2];//'area_oper=todos&ini=01/11/2015&fim=30/11/2015&seg=3';	
  page2.viewportSize = { width: 1280, height: 768 };
  page2.open('http://localhost:8081/Quest/index.jsp','POST', postBody, function(status) {
  		console.log('internal status: '+status);
      //document.body.setAttribute("class", "mini-navbar");
      

    setTimeout(function(){

        var x = page2.evaluate(function() {
             // ocultando
             //document.getElementsByTagName('nav')[0].setAttribute("class","none"); 
             //document.getElementById('page-wrapper').setAttribute("class","menu-invisible");
             //document.getElementById('flot-bar-chart').style.marginLeft="150px";
             //document.getElementById('flot-bar-chart').style.width="auto";
            // document.body.setAttribute("class", "body-small");
             //document.body.setAttribute("class", "mini-navbar");
        });
         console.log(x);
  			  // era aqui
          page2.dpi=72; 
          page2.render('/Users/belchiorpalma/NetBeansProjects/Quest/build/web/js/phantomjs/q.png', {format: 'png', pool: 1,paperFormat : 'A4'});
          phantom.exit(); 
        }, 500);
  		 
  });
  //phantom.exit();
});

