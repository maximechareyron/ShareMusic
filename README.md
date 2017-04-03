# Share Music
*CHAREYRON Maxime PE 2 – MARTIN Olivier IPI*
*Durée du projet : 7 semaines (40 heures de TD)*

----



## Contexte :
Le but de notre application est de faciliter le choix des musiques lorsque l’on se retrouve avec des amis. Le problème étant que chacun voudrait mettre sa musique. Notre application propose donc de choisir un smartphone qui jouera la musique dit « *hébergeur* ». A partir de là, les autres personnes auront juste à utiliser la partie dite « *émetteur* » pour envoyer la musique qu’elles souhaitent.
Pour ajouter une musique à la playlist partagée, elle doivent tout simplement saisir le titre et/ou l'artiste d'une musique, et l'application se charge d'aller chercher la musique pour l’envoyer sur le smartphone qui héberge la playlist. Ce dernier aura un rôle d’administrateur puisqu'il pourra gérer les musiques reçu (suppression, passer la musique, en ajouter d'autres...)

### Choix d’API :
Nous utilisons l’API 11 pour utiliser l’API YouTube qui permet de lire les musiques ainsi que de saisir une musique avec juste un mot clé. Notre application est donc accessible à 100% des utilisateurs.

### Scénario de cas d’utilisations :

**Partie "*Emetteur*" :**

 - L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
 - L’utilisateur choisie la fonction « Emetteur »
 - L’utilisateur tape l’ID party (Chaîne de caractère unique à demander à l'*hébergeur*)
 - L’utilisateur saisi un ou plusieurs mots-clefs de la musique qu’il veut sélectionner.
	 - La musique est alors ajoutée en temps-réel à la playlist de l'*hébergeur*.


**Partie "*Hébergeur*" :**

 - L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
 - L’utilisateur choisie la fonction « Hébergeur »
 - L’utilisateur peut stopper/ passer/ supprimer n’importe quelles musiques.


**Partie "*Paramètres du compte*"  :**

 - L’utilisateur se connecte à Fire Base, ou peut s’y inscrire.
 - L’utilisateur choisie la fonction « Manage Account »
 - L’utilisateur peut changer son adresse mail ou mot de passe, récupérer son mot de passe, supprimer son compte ou encore ce déconnecter.


## Perspectives d'amélioration


- L'hébergeur ne peut pas encore ajouter de musiques à sa playlist.
- Taper le lien de la playlist est long et fastidieux. Il faudrait pouvoir partager ce lien facilement, ou pouvoir utiliser un lien personnalisé.
- Lorsqu'on supprime un titre de la playlist, cette dernière reprend au début.
- Gestion plus approfondie de la rotation d'écran
