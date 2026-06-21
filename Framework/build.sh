#!/bin/bash
# =======================================================
# Build du Framework MVC
# Auteur : Jaona
# =======================================================

# 🔹 Configuration
FRAMEWORK_NAME="FrameworkMVC"

PROJECT_DIR="/home/jaona/Bureau/MrNaina/Framework"
SRC_DIR="$PROJECT_DIR/src"
LIB_DIR="$PROJECT_DIR/lib"

BUILD_DIR="$PROJECT_DIR/build"
DIST_DIR="$PROJECT_DIR/dist"

# 🔹 Nettoyage
echo "🧹 Nettoyage..."
rm -rf "$BUILD_DIR" "$DIST_DIR"

mkdir -p "$BUILD_DIR"
mkdir -p "$DIST_DIR"

# 🔹 Compilation
echo "⚙️ Compilation du framework..."

javac \
-d "$BUILD_DIR" \
-cp "$LIB_DIR/*" \
$(find "$SRC_DIR" -name "*.java")

if [ $? -ne 0 ]; then
    echo "❌ Erreur de compilation"
    exit 1
fi

# 🔹 Création du JAR
echo "📦 Création du JAR..."

jar cvf "$DIST_DIR/$FRAMEWORK_NAME.jar" \
-C "$BUILD_DIR" .

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la création du JAR"
    exit 1
fi

echo ""
echo "✅ Build Framework terminé !"
echo "📦 JAR généré :"
echo "   $DIST_DIR/$FRAMEWORK_NAME.jar"