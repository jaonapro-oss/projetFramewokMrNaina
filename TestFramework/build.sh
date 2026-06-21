#!/bin/bash
# =======================================================
# Build Projet Test Framework MVC
# Auteur : Jaona
# =======================================================

set -e

# 🔹 Configuration
APP_NAME="TestFramework"

PROJECT_DIR="/home/jaona/Bureau/MrNaina/TestFramework"

SRC_DIR="$PROJECT_DIR/java"
WEB_DIR="$PROJECT_DIR/web"
LIB_DIR="$PROJECT_DIR/lib"

FRAMEWORK_JAR="/home/jaona/Bureau/MrNaina/Framework/dist/FrameworkMVC.jar"

BUILD_DIR="$PROJECT_DIR/build"

DEST_DIR="/home/jaona/Bureau/docker/DATA/webapps/$APP_NAME"

# =======================================================
# Nettoyage
# =======================================================

echo "🧹 Nettoyage..."

rm -rf "$BUILD_DIR"
rm -rf "$DEST_DIR"

mkdir -p "$BUILD_DIR/WEB-INF/classes"
mkdir -p "$BUILD_DIR/WEB-INF/lib"

# =======================================================
# Vérification Framework
# =======================================================

if [ ! -f "$FRAMEWORK_JAR" ]; then
    echo "❌ Framework introuvable :"
    echo "$FRAMEWORK_JAR"
    exit 1
fi

# =======================================================
# Copie du Framework
# =======================================================

echo "📦 Copie du framework..."

cp "$FRAMEWORK_JAR" \
"$BUILD_DIR/WEB-INF/lib/"

# =======================================================
# Copie des bibliothèques du projet
# =======================================================

if [ -d "$LIB_DIR" ]; then

    if ls "$LIB_DIR"/*.jar >/dev/null 2>&1; then

        echo "📚 Copie des librairies..."

        cp "$LIB_DIR"/*.jar \
        "$BUILD_DIR/WEB-INF/lib/"

    fi

fi

# =======================================================
# Recherche des fichiers Java
# =======================================================

echo "🔍 Recherche des fichiers Java..."

if [ -d "$SRC_DIR" ]; then
    JAVA_FILES=$(find "$SRC_DIR" -type f -name "*.java")
else
    JAVA_FILES=""
fi

# =======================================================
# Compilation
# =======================================================

if [ -n "$JAVA_FILES" ]; then

    echo "⚙️ Compilation du projet..."

    javac \
    -d "$BUILD_DIR/WEB-INF/classes" \
    -cp "$BUILD_DIR/WEB-INF/lib/*" \
    $JAVA_FILES

    echo "✅ Compilation terminée"

else

    echo "ℹ️ Aucun fichier Java à compiler"

fi

# =======================================================
# Copie des ressources Web
# =======================================================

echo "📄 Copie des fichiers web..."

if [ -d "$WEB_DIR" ]; then
    cp -r "$WEB_DIR"/* "$BUILD_DIR/"
fi

# =======================================================
# Déploiement Tomcat
# =======================================================

echo "🚀 Déploiement..."

mkdir -p "$(dirname "$DEST_DIR")"

cp -r "$BUILD_DIR" "$DEST_DIR"

# =======================================================
# Génération WAR
# =======================================================

echo "📦 Création du WAR..."

cd "$BUILD_DIR"

jar cvf "../$APP_NAME.war" . > /dev/null

cd ..

# =======================================================
# Résumé
# =======================================================

echo ""
echo "======================================="
echo "✅ BUILD TERMINÉ"
echo "======================================="
echo "📁 Projet déployé :"
echo "   $DEST_DIR"
echo ""
echo "📦 WAR généré :"
echo "   $PROJECT_DIR/$APP_NAME.war"
echo "======================================="