# Captcha project

## Installation
<h3>Compilation</h3> 

<i>javac -sourcepath sources -d classes sources/fr/upem/captcha/Main.java && cp sources/META-INF/MANIFEST.MF classes</i>

<h3>Récupération des fichiers images</h3>

<i>cp sources/fr/upem/captcha/images/panneaux/*jpg classes/fr/upem/captcha/images/panneaux</i><br/>
<i>cp sources/fr/upem/captcha/images/voitures/*jpg classes/fr/upem/captcha/images/voitures</i>

<h3>Récupération de l'exécutable jar</h3>

<i>cd classes</i><br/>
<i>jar cvmf META-INF/MANIFEST.MF CaptchaProject.jar *</i>

<h3>Execution</h3>

<i>java -jar CaptchaProject.jar</i>
