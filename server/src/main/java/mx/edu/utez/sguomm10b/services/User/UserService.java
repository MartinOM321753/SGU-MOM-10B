package mx.edu.utez.sguomm10b.services.User;

import mx.edu.utez.sguomm10b.models.User.UserEntity;
import mx.edu.utez.sguomm10b.models.User.UserRepository;

import mx.edu.utez.sguomm10b.utils.APIResponse;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public ResponseEntity<APIResponse> save(UserEntity userEntity) {
        try {
            if (userEntity.getName() == null || userEntity.getName().isEmpty()) {
                return ResponseEntity.badRequest().body(
                    new APIResponse(null, "El nombre es obligatorio.", HttpStatus.BAD_REQUEST, true)
                );
            }
            if (userEntity.getEmail() == null || userEntity.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(
                    new APIResponse(null, "El correo es obligatorio.", HttpStatus.BAD_REQUEST, true)
                );
            }

            UserEntity saved = userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponse(saved, "Usuario creado exitosamente", HttpStatus.CREATED, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(null, "Error al crear usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true));
        }
    }

    public ResponseEntity<APIResponse> findAll() {
        try {
            List<UserEntity> users = userRepository.findAll();
            return ResponseEntity.ok(new APIResponse(users, "Listado de usuarios", HttpStatus.OK, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(null, "Error al consultar usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true));
        }
    }

    public ResponseEntity<APIResponse> findById(Long id) {
        try {
            Optional<UserEntity> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                return ResponseEntity.ok(new APIResponse(optional.get(), "Usuario encontrado", HttpStatus.OK, false));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new APIResponse(null, "Usuario no encontrado", HttpStatus.NOT_FOUND, true));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(null, "Error al buscar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true));
        }
    }

    // UPDATE
    public ResponseEntity<APIResponse> update(Long id, UserEntity userEntity) {
        try {
            Optional<UserEntity> optional = userRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new APIResponse(null, "Usuario no encontrado", HttpStatus.NOT_FOUND, true));
            }
            UserEntity existing = optional.get();
            // Validaciones básicas
            if (userEntity.getName() == null || userEntity.getName().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new APIResponse(null, "El nombre es obligatorio.", HttpStatus.BAD_REQUEST, true)
                );
            }
            if (userEntity.getEmail() == null || userEntity.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new APIResponse(null, "El correo es obligatorio.", HttpStatus.BAD_REQUEST, true)
                );
            }
            existing.setName(userEntity.getName());
            existing.setLastName(userEntity.getLastName());
            existing.setEmail(userEntity.getEmail());
            existing.setPhoneNumber(userEntity.getPhoneNumber());
            userRepository.save(existing);
            return ResponseEntity.ok(new APIResponse(existing, "Usuario actualizado", HttpStatus.OK, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(null, "Error al actualizar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true));
        }
    }

    public ResponseEntity<APIResponse> delete(Long id) {
        try {
            Optional<UserEntity> optional = userRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new APIResponse(null, "Usuario no encontrado", HttpStatus.NOT_FOUND, true));
            }
            userRepository.deleteById(id);
            return ResponseEntity.ok(new APIResponse(null, "Usuario eliminado correctamente", HttpStatus.OK, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(null, "Error al eliminar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true));
        }
    }
}