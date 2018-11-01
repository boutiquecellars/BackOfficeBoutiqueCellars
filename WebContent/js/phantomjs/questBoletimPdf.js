var args = require('system').args;
var postBody = args[1];
var page = require('webpage').create();

//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1280, height: 768 };
page.open('http://localhost:8081/Quest_Iveco/Login','POST', postBody, function(status) {
  console.log('status: '+status);

 	var page2 = require('webpage').create();
   var postBody = args[2];	
   /*
    page2.settings.resourceTimeout = 5000; // 5 seconds
    page2.onResourceTimeout = function(e) {
      console.log(e.errorCode);   // it'll probably be 408 
      console.log(e.errorString); // it'll probably be 'Network timeout on resource'
      console.log(e.url);         // the url whose request timed out
      phantom.exit(1);
    };
  */
  page2.viewportSize = { width: 1280, height: 768 };
  page2.open('http://localhost:8081/Quest_Iveco/boletim.jsp','POST', postBody, function(status) {
  	//	console.log('internal status: '+status);
      //document.body.setAttribute("class", "mini-navbar");
      
      page2.paperSize = {
            format: 'A4',
            orientation: 'portrait',
             margin: {
            top: '20px',
            left: '0px'
          }
        };
      /*
      page2.paperSize = {
        width: '8.5in',
        height: '11in',
        header: {
          height: "1cm",
          contents: phantom.callback(function(pageNum, numPages) {
            return "<h3> <span style='float:right'>" + pageNum + " / " + numPages + "</span></h3>";
          })
        },
        footer: {
          height: "1cm",
          contents: phantom.callback(function(pageNum, numPages) {
            return "<h3> <span style='float:right'>" + pageNum + " / " + numPages + "</span></h3>";
          })
        }
      }*/
    
    
    setTimeout(function(){
        //page2.zoomFactor = 0.75;
        var x = page2.evaluate(function() {
             //zoom
             document.body.style.zoom="50%";
            // $("#row2").css("height","550px");
             $(".navbar").hide();
             $("body").css("background-color","white");
             $(".pace").hide();
             $(".row.border-bottom").hide();
             $("#row3").css("page-break-before","always");
             $("#row3").css("margin-top","100px");
             $("#row5").css("page-break-before","always");
             $("#row7").css("page-break-before","always");
           // document.getElementById('small').innerHTML="<br/>";
            //document.getElementById('li-lista-cliente').style.display="none";
           // document.getElementById('li-pdf').style.display="none";
            // document.body.setAttribute("class", "body-small");
             //document.body.setAttribute("class", "mini-navbar");
        });
        // console.log(x);
  			  // era aqui
          page2.dpi=72; 
          page2.render('/Users/belchiorpalma/NetBeansProjects/Quest_Iveco/build/web/js/phantomjs/quest_boletim.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
          //page2.render('/usr/local/apache-tomcat-8.0.28/webapps/Quest/js/phantomjs/q.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
          
            phantom.exit();
          
        }, 15000);
  		 
  });
  //phantom.exit();
});



