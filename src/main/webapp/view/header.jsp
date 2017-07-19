<div class="menu container-fluid">
    <div class="row">


        <%--    <div class="col-md-2 menu">

                    <a href="/">Home</a>
            </div>--%>

        <div class="col-md-2 menu">
            <a href="/" class="lnk"><fmt:message key="main.page"/></a>
        </div>

            <div class="col-md-2 menu">
                <a href="/apartments" class="lnk"><fmt:message key="freeApartments.page"/></a>
            </div>

        <%--           <div class="col-md-2 menu">
                   <form action="/" method="post">
                           <input type="hidden" name="command" value="redirect"/>
                           <input type="hidden" name="page" value="index"/>
                           <button type="submit"><fmt:message key="main.page"/></button>
                       </form>
               </div>--%>


        <c:choose>
            <c:when test="${user == null}">

                <div class="col-md-2 menu">
                    <a href="/login"><fmt:message key="login.page"/></a>
                </div>

                <%--   <div class="col-md-2 menu">
                   <form action="/login" method="get">
                       <input type="hidden" name="command" value="redirect"/>
                       <input type="hidden" name="page" value="login"/>
                       <button class="btn dropdown-toggle language-btn" type="submit"><fmt:message key="login.page"/></button>
                   </form>
                   </div>--%>

                <div class="col-md-2 menu">
                    <a href="/registration"><fmt:message key="registration.page"/></a>
                </div>

                <%--<div class="col-md-2 menu">
                <form action="/registration" method="post">
                    <input type="hidden" name="command" value="redirect"/>
                    <input type="hidden" name="page" value="registration"/>
                    <button type="submit"><fmt:message key="registration.page"/></button>
                </form>
                </div>--%>

            </c:when>


            <c:when test="${user != null}">
                <div class="col-md-2 menu">
                    <a href="/profile"><fmt:message key="profile.page"/></a>
                </div>
                <%--<form action="/profile" method="post">
                    <input type="hidden" name="command" value="redirect"/>
                    <input type="hidden" name="page" value="profile"/>
                    <button type="submit"><fmt:message key="profile.page"/></button>
                </form>--%>
                <div class="col-md-2 menu">
                    <p class="text">
                        <fmt:message key="header.logged"/>
                        <c:out value="${user.userAuthentication.login}"/>
                    </p>
                </div>

                <div class="col-md-2 menu">
                    <a href="/logout"><fmt:message key="logout"/></a>
                </div>


                <%--<form action="/logout" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <button type="submit"><fmt:message key="logout"/></button>
                </form>--%>


                <%--POST INSTEAD GET--%>
                <%-- <form action="your_url" method="post">
                         <button type="submit" name="your_name" value="your_value" class="btn-link">Go</button>
                     </form>--%>
            </c:when>

        </c:choose>


        <%--
                 <form>
                     <div class="col-md-2 menu outter_div">
                     <select class="select-lang" id="language" name="language" onchange="submit()">
                         <option class="select-lang" value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
                         <option class="select-lang" value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukr</option>
                         <option class="select-lang" value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Ru</option>
                    </select>
                     </div>
                 </form>--%>

        <form method="get">
            <div class="col-md-2 menu">
                <div class="dropdown">
                    <button class="btn dropdown-toggle language-btn" type="button" data-toggle="dropdown"> Language
                    </button>
                    <button class="btn dropdown-toggle language-btn" data-toggle="dropdown"><span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="?language=en_US">English</a></li>
                        <%--<li><a href="?language=uk_UA">Ukrainian</a></li>--%>
                        <li><a href="?language=ru_RU">Russian</a></li>
                    </ul>
                </div>
            </div>
        </form>


    </div>

    <div class="row ">

        <div class="col-md-4">
        </div>

        <div class="col-md-4 ">
