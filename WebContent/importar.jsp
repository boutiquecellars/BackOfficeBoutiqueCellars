<%@page import="br.com.itfox.config.Preferences"%>
<%@include file="incProfileAdmin.jsp" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- Force latest IE rendering engine or ChromeFrame if installed -->
        <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
        <meta charset="utf-8">
        <title><%=Preferences.title%></title>
        <meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
        <meta name="viewport" content="width=device-width">
        <!-- Bootstrap CSS Toolkit styles -->
        <!--
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        -->
        <!-- Generic page styles -->
        <link rel="stylesheet" href="css/style-upload.css">
        <!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
        <!--<link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-responsive.min.css">-->
        <!-- Bootstrap CSS fixes for IE6 -->
        <!--[if lt IE 7]><link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-ie6.min.css"><![endif]-->
        <!-- Bootstrap Image Gallery styles -->
        <link rel="stylesheet" href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
        <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
        <link rel="stylesheet" href="css/jquery.fileupload-ui.css">
        <!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->
        <!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

</head>

<body class="fixed-sidebar">

<!-- The Gallery as lightbox dialog, should be a child element of the document body -->
<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>

<div id="wrapper">

     <jsp:include page="incNavSide.jsp" >
     <jsp:param name="page" value="importar" />
    </jsp:include>
     
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
             <jsp:include page="incNavbarStaticTop.jsp" />
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            
            <!-- row upload -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="text-left m-t-lg">
                    	<!-- drag and drop -->
                        <div class="col-sm-12 main">
                    <!--<div class="container">-->
                        <div class="page-header">
                            <h1><%=Preferences.h1%></h1>
                        </div>
                    <div class="row placeholders">
                        <blockquote>
                            <p>Selecione e arraste os arquivos para efetuar os uploads.</p>
                        </blockquote>
                        <br>
                        <!-- The file upload form used as target for the file upload widget -->
                        <form id="fileupload" action="UploadServlet" method="POST" enctype="multipart/form-data">
                            <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                            <div class="row fileupload-buttonbar">
                                <div class="span7">
                                    <!-- The fileinput-button span is used to style the file input field as button -->
                                    <span class="btn btn-success fileinput-button">
                                        <i class="icon-plus icon-white"></i>
                                        <span>Adicionar arquivos...</span>
                                        <input type="file" name="files[]" multiple>
                                    </span>
                                    <button type="submit" class="btn btn-primary start">
                                        <i class="icon-upload icon-white"></i>
                                        <span>Iniciar upload</span>
                                    </button>
                                    <button type="reset" class="btn btn-warning cancel">
                                        <i class="icon-ban-circle icon-white"></i>
                                        <span>Cancelar upload</span>
                                    </button>
                                    <button type="button" class="btn btn-danger delete">
                                        <i class="icon-trash icon-white"></i>
                                        <span>Delete</span>
                                    </button>
                                    <input type="checkbox" class="toggle">
                                </div>
                                <!-- The global progress information -->
                                <div class="span5 fileupload-progress fade">
                                    <!-- The global progress bar -->
                                    <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                                        <div class="bar" style="width:0%;"></div>
                                    </div>
                                    <!-- The extended global progress information -->
                                    <div class="progress-extended">&nbsp;</div>
                                </div>
                            </div>
                            <!-- The loading indicator is shown during file processing -->
                            <div class="fileupload-loading"></div>
                            <br>
                            <!-- The table listing the files available for upload/download -->
                            <!--
                            <table role="presentation" class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
                            -->
                            <table role="presentation" class="table table-striped table-curved">
                                <thead >
                                    <tr>
            
                                        <th >
                                            Filtros
                                        </th>
                                        <th >
                                            <select>
                                                <option value="volvo">A-Z</option>
                                                <option value="saab">Z-A</option>
                                              </select>
                                            <select>
                                                <option value="volvo">Por Data</option>
                                                <option value="volvo">04-11-2015</option>
                                                <option value="saab">05-11-2015</option>
                                              </select>
                                            
                                            <input type="search" name="keyword" id="keyword"/>
                                            
                                        </th>
                                        <th >
                                                <button class="btn btn-search" data-type="GET" data-url="UploadServlet?delfile=IMG_8581.jpg">
                                                <i class="icon-play icon-white"></i>
                                                <span>Search</span>
                                            </button>
                                        </th>
                                        <th ></th>
                                        <th ></th>    
                                        <th >
                                            <button class="btn btn-danger" data-type="GET" data-url="UploadServlet?delfile=IMG_8581.jpg">
                                                <i class="icon-trash icon-white"></i>
                                                <span>Delete</span>
                                            </button>
                                            <input type="checkbox" class="toggle">
                                        </th>
                                    </tr>
                                </thead>
                                <tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery">
                                    
                                </tbody>
                            </table>
                        </form>
                        <br>
                        <!--
                        <div class="well">
                            <h3>Notas Explicativas</h3>
                            <ul>
                                <li>The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).</li>
                                <li>Only image files (<strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction).</li>
                                <li>Uploaded files will be deleted automatically after <strong>5 minutes</strong> (demo setting).</li>
                                <li>You can <strong>drag &amp; drop</strong> files from your desktop on this webpage with Google Chrome, Mozilla Firefox and Apple Safari.</li>
                                <li>Please refer to the <a href="https://github.com/blueimp/jQuery-File-Upload">project website</a> and <a href="https://github.com/blueimp/jQuery-File-Upload/wiki">documentation</a> for more information.</li>
                                <li>Built with Twitter's <a href="http://twitter.github.com/bootstrap/">Bootstrap</a> toolkit and Icons from <a href="http://glyphicons.com/">Glyphicons</a>.</li>
                            </ul>
                        </div>
                        -->
                    <!--</div>-->
                    <!-- modal-gallery is the modal dialog used for the image gallery -->
                    <div id="modal-gallery" class="modal modal-gallery hide fade" data-filter=":odd">
                        <div class="modal-header">
                            <a class="close" data-dismiss="modal">&times;</a>
                            <h3 class="modal-title"></h3>
                        </div>
                        <div class="modal-body"><div class="modal-image"></div></div>
                        <div class="modal-footer">
                            <a class="btn modal-download" target="_blank">
                                <i class="icon-download"></i>
                                <span>Download</span>
                            </a>
                            <a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000">
                                <i class="icon-play icon-white"></i>
                                <span>Slideshow</span>
                            </a>
                            <a class="btn btn-info modal-prev">
                                <i class="icon-arrow-left icon-white"></i>
                                <span>Previous</span>
                            </a>
                            <a class="btn btn-primary modal-next">
                                <span>Next</span>
                                <i class="icon-arrow-right icon-white"></i>
                            </a>
                        </div>
                    </div>
                </div>
                    </div>


            </div>
                        <!--// drag and drop -->
                        
                    </div>
                </div>
            </div>
            <!--// row upload -->
            <!-- footer -->
            <div class="footer">
                <!--
                <div class="pull-right">
                    10GB of <strong>250GB</strong> Free.
                </div>
                <div>
                    <strong>Copyright</strong> Example Company &copy; 2014-2015
                </div>
                -->
        	</div>
            <!--// footer -->
        </div>
       

    </div>


<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.js"></script>

<!-- drag and drop by itfox -->
 <!-- The template to display files available for upload -->
        <script id="template-upload" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-upload fade">
            <td class="preview"><span class="fade"></span></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
            {% } else if (o.files.valid && !i) { %}
            <td>
            <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
            <button class="btn btn-primary">
            <i class="icon-upload icon-white"></i>
            <span>{%=locale.fileupload.start%}</span>
            </button>
            {% } %}</td>
            {% } else { %}
            <td colspan="2"></td>
            {% } %}
            <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
            <i class="icon-ban-circle icon-white"></i>
            <span>{%=locale.fileupload.cancel%}</span>
            </button>
            {% } %}</td>
            </tr>
            {% } %}
        </script>
        <!-- The template to display files available for download -->
        <script id="template-download" type="text/x-tmpl">
            {% 
                      
                for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-download fade">
            {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
            {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
            <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img src="UploadServlet?getthumb={%=file.icon%}"></a>
            {% } %}</td>
            <td class="name">
            <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}<span>{%=file.status%} - {%=file.statusIcon%}</span></span></td>
            <td colspan="2"></td>
            {% } %}
            <td class="delete">
            <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}">
            <i class="icon-trash icon-white"></i>
            <span>{%=locale.fileupload.destroy%}</span>
            </button>
            <input type="checkbox" name="delete" value="1">
            </td>
            </tr>
            {% } %}
        </script>
        <!--
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        -->
        <!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
        <script src="js/vendor/jquery.ui.widget.js"></script>
        <!-- The Templates plugin is included to render the upload/download listings -->
        <script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
        <!-- The Load Image plugin is included for the preview images and image resizing functionality -->
        <script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.js"></script>
        <!-- The Canvas to Blob plugin is included for image resizing functionality -->
        <script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
        <!-- Bootstrap JS and Bootstrap Image Gallery are not required, but included for the demo -->
        <!--
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
        -->
        <script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
        <!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
        <script src="js/jquery.iframe-transport.js"></script>
        <!-- The basic File Upload plugin -->
        <script src="js/jquery.fileupload.js"></script>
        <!-- The File Upload file processing plugin -->
        <script src="js/jquery.fileupload-fp.js"></script>
        <!-- The File Upload user interface plugin -->
        <script src="js/jquery.fileupload-ui.js"></script>
        <!-- The localization script -->
        <script src="js/locale.js"></script>
        <!-- The main application script -->
        <script src="js/main.js"></script>
        <!-- The XDomainRequest Transport is included for cross-domain file deletion for IE8+ -->
        <!--[if gte IE 8]><script src="js/cors/jquery.xdr-transport.js"></script><![endif]-->
        
        
     

</body>

</html>
