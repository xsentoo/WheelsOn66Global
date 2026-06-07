import React, { useState } from 'react';
import {
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
  Alert,
  StatusBar
} from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

export default function App() {
  const [mail, setMail] = useState('');
  const [password, setPassword] = useState('');
  const [pseudo, setPseudo] = useState('');
  const [isLoginMode, setIsLoginMode] = useState(true);

  const handleLogin = async () => {
    if (!mail || !password) {
      Alert.alert('Oups', 'Veuillez remplir tous les champs pour démarrer le road trip.');
      return;
    }
    try {
      const response = await fetch('http://192.168.0.10:8082/api/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email: mail,
          rawPassword: password
        })
      });

      if (response.ok) {

        Alert.alert('Succès', 'Connexion réussie, en route !');
      } else {
        Alert.alert('Erreur', 'Identifiants incorrects.');
      }
    } catch (error) {
      Alert.alert('Erreur réseau', 'Impossible de joindre le serveur.');
      console.error(error);
    }
  };

  const handleRegister = async () => {

    if (!mail || !password || !pseudo) {
      Alert.alert('Erreur', 'Veuillez remplir tous les champs');
      return;
    }

    try {
      const Registerresponse = await fetch('http://192.168.0.10:8082/api/users/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email: mail,
          rawPassword: password,
          pseudo: pseudo
        })
      });

      if (Registerresponse.ok) {
        Alert.alert('Succès', 'Inscription réussie ! Tu peux maintenant te connecter.');
        setIsLoginMode(true);
        setPassword('');
        setPseudo('');
      } else {
        Alert.alert('Erreur', 'Cet email existe déjà ou les données sont invalides.');
      }

    } catch (error) {
      Alert.alert('Erreur réseau', 'Impossible de joindre le serveur pour l\'inscription.');
      console.error(error);
    }
  };

  return (
      <SafeAreaProvider>
        <SafeAreaView style={styles.container}>
          <StatusBar barStyle="dark-content" />

          <View style={styles.header}>
            <Text style={styles.title}>WheelsOn66</Text>
            <Text style={styles.subtitle}>
              {isLoginMode ? 'Prêt pour le départ ?' : 'Rejoins l\'aventure !'}
            </Text>
          </View>

          <View style={styles.form}>
            {!isLoginMode && (
                <TextInput
                    style={styles.input}
                    placeholder="Ton Pseudo"
                    value={pseudo}
                    onChangeText={setPseudo}
                    autoCapitalize="words"
                />
            )}

            <TextInput
                style={styles.input}
                placeholder="Adresse Email"
                value={mail}
                onChangeText={setMail}
                keyboardType="email-address"
                autoCapitalize="none"
            />

            <TextInput
                style={styles.input}
                placeholder="Mot de passe"
                value={password}
                onChangeText={setPassword}
                secureTextEntry
            />

            {isLoginMode ? (
                <>
                  <TouchableOpacity style={styles.button} onPress={handleLogin}>
                    <Text style={styles.buttonText}>Se connecter</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.switchButton}
                      onPress={() => setIsLoginMode(false)}
                  >
                    <Text style={styles.switchButtonText}>Pas encore de compte ? S'inscrire</Text>
                  </TouchableOpacity>
                </>
            ) : (
                <>
                  <TouchableOpacity style={styles.button} onPress={handleRegister}>
                    <Text style={styles.buttonText}>Créer mon compte</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.switchButton}
                      onPress={() => setIsLoginMode(true)}
                  >
                    <Text style={styles.switchButtonText}>Déjà un compte ? Se connecter</Text>
                  </TouchableOpacity>
                </>
            )}

          </View>
        </SafeAreaView>
      </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FAFAFA',
    justifyContent: 'center',
    padding: 20,
  },
  header: {
    alignItems: 'center',
    marginBottom: 40,
  },
  title: {
    fontSize: 36,
    fontWeight: 'bold',
    color: '#2C3E50',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 18,
    color: '#7F8C8D',
  },
  form: {
    width: '100%',
  },
  input: {
    height: 55,
    backgroundColor: '#FFFFFF',
    borderColor: '#E0E0E0',
    borderWidth: 1,
    borderRadius: 10,
    paddingHorizontal: 15,
    marginBottom: 15,
    fontSize: 16,
  },
  button: {
    height: 55,
    backgroundColor: '#D35400',
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 10,
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 18,
    fontWeight: 'bold',
  },
  switchButton: {
    marginTop: 20,
    alignItems: 'center',
  },
  switchButtonText: {
    color: '#2980B9',
    fontSize: 16,
    fontWeight: '600',
  }
});