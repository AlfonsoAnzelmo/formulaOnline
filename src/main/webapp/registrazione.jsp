<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>Formula Online</title>
</head>
<body>
<%
    if(session.getAttribute("lettore")!=null)
        response.sendRedirect("home");
%>
<%@include file="/WEB-INF/header.jsp"%>
<div class="container main h-100 pt-4 pb-4 justify-content-center">
    <div class="w-100 d-grid justify-content-center">
        <div class="row text-center p-3">
            <h3>
                Registrazione lettore
            </h3>
        </div>
        <form action="registrazione" method="post">
            <div class="row mb-3 form-floating">
                <input name="email" type="text" class="form-control" id="validationMail" placeholder required>
                <label for="validationMail" class="form-label">e-mail</label>
            </div>
            <div class="row mb-3 form-floating">
                <input name="password" type="password" class="form-control" id="validationPassword" placeholder required>
                <label for="validationPassword" class="form-label">Password</label>
            </div>
            <div class="row mb-3 form-floating">
                <input name="nickname" type="text" class="form-control" id="validationNickname" placeholder required>
                <label for="validationNickname" class="form-label">Nickname</label>
            </div>
            <div class="row mb-3">
                <label for="scuderia" class="form-label">Scuderia Preferita</label>
                <select class="form-select" name="scuderia" id="scuderia">
                    <option value="nessuna" selected>Nessuna</option>    <option value="McLaren">McLaren</option>       <option value="BMW Sauber">BMW Sauber</option>           <option value="Williams">Williams</option>             <option value="Renault">Renault</option>       <option value="Toro Rosso">Toro Rosso</option>             <option value="Ferrari">Ferrari</option>               <option value="Toyota">Toyota</option>     <option value="Super Aguri">Super Aguri</option>           <option value="Red Bull">Red Bull</option>     <option value="Force India">Force India</option>                 <option value="Honda">Honda</option>               <option value="Spyker">Spyker</option>                     <option value="MF1">MF1</option>       <option value="Spyker MF1">Spyker MF1</option>               <option value="Sauber">Sauber</option>                     <option value="BAR">BAR</option>               <option value="Jordan">Jordan</option>             <option value="Minardi">Minardi</option>               <option value="Jaguar">Jaguar</option>                 <option value="Prost">Prost</option>               <option value="Arrows">Arrows</option>           <option value="Benetton">Benetton</option>                 <option value="Brawn">Brawn</option>             <option value="Stewart">Stewart</option>             <option value="Tyrrell">Tyrrell</option>                   <option value="Lola">Lola</option>               <option value="Ligier">Ligier</option>                 <option value="Forti">Forti</option>           <option value="Footwork">Footwork</option>             <option value="Pacific">Pacific</option>               <option value="Simtek">Simtek</option>       <option value="Team Lotus">Team Lotus</option>         <option value="Larrousse">Larrousse</option>             <option value="Brabham">Brabham</option>             <option value="Dallara">Dallara</option>         <option value="Fondmetal">Fondmetal</option>                 <option value="March">March</option>     <option value="Andrea Moda">Andrea Moda</option>                     <option value="AGS">AGS</option>                 <option value="Lambo">Lambo</option>   <option value="Leyton House">Leyton House</option>               <option value="Coloni">Coloni</option>         <option value="Euro Brun">Euro Brun</option>               <option value="Osella">Osella</option>                   <option value="Onyx">Onyx</option>                   <option value="Life">Life</option>                   <option value="Rial">Rial</option>           <option value="Zakspeed">Zakspeed</option>                     <option value="RAM">RAM</option>       <option value="Alfa Romeo">Alfa Romeo</option>               <option value="Spirit">Spirit</option>             <option value="Toleman">Toleman</option>                     <option value="ATS">ATS</option>           <option value="Theodore">Theodore</option>       <option value="Fittipaldi">Fittipaldi</option>               <option value="Ensign">Ensign</option>               <option value="Shadow">Shadow</option>                   <option value="Wolf">Wolf</option>           <option value="Merzario">Merzario</option>             <option value="Kauhsen">Kauhsen</option>             <option value="Rebaque">Rebaque</option>             <option value="Surtees">Surtees</option>             <option value="Hesketh">Hesketh</option>             <option value="Martini">Martini</option>                     <option value="BRM">BRM</option>               <option value="Penske">Penske</option>                     <option value="LEC">LEC</option>             <option value="McGuire">McGuire</option>                   <option value="Boro">Boro</option>             <option value="Apollon">Apollon</option>               <option value="Kojima">Kojima</option>           <option value="Parnelli">Parnelli</option>                   <option value="Maki">Maki</option>   <option value="Embassy Hill">Embassy Hill</option>               <option value="Lyncar">Lyncar</option>               <option value="Trojan">Trojan</option>                   <option value="Amon">Amon</option>                 <option value="Token">Token</option>   <option value="Iso Marlboro">Iso Marlboro</option>                 <option value="Tecno">Tecno</option>                 <option value="Matra">Matra</option>           <option value="Politoys">Politoys</option>               <option value="Connew">Connew</option>             <option value="Bellasi">Bellasi</option>         <option value="De Tomaso">De Tomaso</option>               <option value="Cooper">Cooper</option>                 <option value="Eagle">Eagle</option>                     <option value="LDS">LDS</option>               <option value="Protos">Protos</option>             <option value="Shannon">Shannon</option>           <option value="Scirocco">Scirocco</option>                       <option value="RE">RE</option>                     <option value="BRP">BRP</option>             <option value="Porsche">Porsche</option>       <option value="Derrington">Derrington</option>                 <option value="Gilby">Gilby</option>               <option value="Stebro">Stebro</option>           <option value="Emeryson">Emeryson</option>                     <option value="ENB">ENB</option>                     <option value="JBW">JBW</option>           <option value="Ferguson">Ferguson</option>                     <option value="MBM">MBM</option> <option value="Behra-Porsche">Behra-Porsche</option>           <option value="Maserati">Maserati</option>               <option value="Scarab">Scarab</option>               <option value="Watson">Watson</option>             <option value="Epperly">Epperly</option>           <option value="Phillips">Phillips</option>           <option value="Lesovsky">Lesovsky</option>               <option value="Trevis">Trevis</option>         <option value="Meskowski">Meskowski</option>   <option value="Kurtis Kraft">Kurtis Kraft</option>                 <option value="Kuzma">Kuzma</option>     <option value="Christensen">Christensen</option>                 <option value="Ewing">Ewing</option>   <option value="Aston Martin">Aston Martin</option>             <option value="Vanwall">Vanwall</option>                 <option value="Moore">Moore</option>                   <option value="Dunn">Dunn</option>                 <option value="Elder">Elder</option>               <option value="Sutton">Sutton</option>                     <option value="Fry">Fry</option>             <option value="Tec-Mec">Tec-Mec</option>         <option value="Connaught">Connaught</option>                   <option value="Alta">Alta</option>                   <option value="OSCA">OSCA</option>             <option value="Gordini">Gordini</option>             <option value="Stevens">Stevens</option>             <option value="Bugatti">Bugatti</option>           <option value="Mercedes">Mercedes</option>               <option value="Lancia">Lancia</option>                     <option value="HWM">HWM</option>         <option value="Schroeder">Schroeder</option>                   <option value="Pawl">Pawl</option>           <option value="Pankratz">Pankratz</option> <option value="Arzani-Volpini">Arzani-Volpini</option>             <option value="Nichels">Nichels</option>               <option value="Bromme">Bromme</option>                 <option value="Klenk">Klenk</option>                 <option value="Simca">Simca</option>               <option value="Turner">Turner</option>             <option value="Del Roy">Del Roy</option>             <option value="Veritas">Veritas</option>                     <option value="BMW">BMW</option>                     <option value="EMW">EMW</option>                     <option value="AFM">AFM</option>     <option value="Frazer Nash">Frazer Nash</option>             <option value="Sherman">Sherman</option>                 <option value="Deidt">Deidt</option>                     <option value="ERA">ERA</option> <option value="Aston Butterworth">Aston Butterwor...         <option value="Cisitalia">Cisitalia</option>     <option value="Talbot-Lago">Talbot-Lago</option>                   <option value="Hall">Hall</option>           <option value="Marchese">Marchese</option>             <option value="Langley">Langley</option>                     <option value="Rae">Rae</option>                 <option value="Olson">Olson</option>         <option value="Wetteroth">Wetteroth</option>                 <option value="Adams">Adams</option>       <option value="Snowberger">Snowberger</option>               <option value="Milano">Milano</option>                     <option value="HRT">HRT</option> <option value="Cooper-Maserati">Cooper-Maserati</option>               <option value="Virgin">Virgin</option>     <option value="Cooper-OSCA">Cooper-OSCA</option> <option value="Cooper-Borgward">Cooper-Borgward</option> <option value="Cooper-Climax">Cooper-Climax</option> <option value="Cooper-Castellotti">Cooper-Castell...   <option value="Lotus-Climax">Lotus-Climax</option> <option value="Lotus-Maserati">Lotus-Maserati</option> <option value="De Tomaso-Osca">De Tomaso-Osca</option> <option value="De Tomaso-Alfa Romeo">De Tomaso-Al...         <option value="Lotus-BRM">Lotus-BRM</option> <option value="Lotus-Borgward">Lotus-Borgward</option> <option value="Cooper-Alfa Romeo">Cooper-Alfa Rom... <option value="De Tomaso-Ferrari">De Tomaso-Ferra...       <option value="Lotus-Ford">Lotus-Ford</option>     <option value="Brabham-BRM">Brabham-BRM</option>   <option value="Brabham-Ford">Brabham-Ford</option> <option value="Brabham-Climax">Brabham-Climax</option>       <option value="LDS-Climax">LDS-Climax</option> <option value="LDS-Alfa Romeo">LDS-Alfa Romeo</option>     <option value="Cooper-Ford">Cooper-Ford</option>   <option value="McLaren-Ford">McLaren-Ford</option> <option value="McLaren-Serenissima">McLaren-Seren...   <option value="Eagle-Climax">Eagle-Climax</option> <option value="Eagle-Weslake">Eagle-Weslake</option> <option value="Brabham-Repco">Brabham-Repco</option> <option value="Cooper-Ferrari">Cooper-Ferrari</option>       <option value="Cooper-ATS">Cooper-ATS</option>     <option value="McLaren-BRM">McLaren-BRM</option>       <option value="Cooper-BRM">Cooper-BRM</option>       <option value="Matra-Ford">Matra-Ford</option>           <option value="BRM-Ford">BRM-Ford</option> <option value="McLaren-Alfa Romeo">McLaren-Alfa R... <option value="March-Alfa Romeo">March-Alfa Romeo...       <option value="March-Ford">March-Ford</option> <option value="Lotus-Pratt &amp; Whitney">Lotus-P...     <option value="Shadow-Ford">Shadow-Ford</option>   <option value="Shadow-Matra">Shadow-Matra</option> <option value="Brabham-Alfa Romeo">Brabham-Alfa R...                 <option value="Lotus">Lotus</option>           <option value="Marussia">Marussia</option>           <option value="Caterham">Caterham</option>           <option value="Lotus F1">Lotus F1</option> <option value="Manor Marussia">Manor Marussia</option>   <option value="Haas F1 Team">Haas F1 Team</option>   <option value="Racing Point">Racing Point</option>       <option value="AlphaTauri">AlphaTauri</option> <option value="Alpine F1 Team">Alpine F1 Team</option>
                </select>

            </div>
            <div class="row">
                <button class="btn btn-primary mb-5" type="submit" value="registrati">Registrati</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>