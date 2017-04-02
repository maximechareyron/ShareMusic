**Share Music**
---------------
CHAREYRON Maxime PE 2 – MARTIN Olivier IPI 2


----------

**Contexte :**
Le but de notre application est de faciliter le choix des musiques lorsque l’on se retrouve avec des amis. Le problème étant que chacun voudrait mettre sa musique. Notre application propose donc de choisir un smartphone qui jouera la musique dit « hébergeur ». A partir de là, les autres personnes auront juste à utiliser la partie dit « émetteur » pour envoyer la musique qu’il souhaite, ils devront saisir un titre et notre application trouvera d’elle-même la musique pour l’envoyer au smartphone qui héberge la playlist. Ce dernier aura un rôle d’administrateur puisqu’il pourra gérer les musiques reçu (suppression, passer la musique)

**Choix d’API :**
Nous utilisons l’API 11 pour utiliser l’API YouTube qui permet de lire les musiques ainsi que de saisir une musique avec juste un mot clé.

**Scénario de cas d’utilisations :**
*Utilisation de la partie Emetteur :*
- L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
- L’utilisateur choisie la fonction « Emetteur »
- L’utilisateur tape l’ID party (mail du compte hébergeur)
- L’utilisateur saisie un ou plusieurs mots clés de la musique qu’il veut sélectionner (la musique sera trouvé et envoyé par l’application).


*Utilisation de la partie Hébergeur :*
- L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
- L’utilisateur choisie la fonction « Hébergeur »
- L’utilisateur peut stopper/ passer/ supprimer n’importe quelles musiques.

*Utilisation de la partie Manage account :*
- L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
- L’utilisateur choisie la fonction « Manage Account »
- L’utilisateur peut changer son adresse mail ou mot de passe, récupérer son mot de passe, supprimer son compte ou encore ce déconnecter.
