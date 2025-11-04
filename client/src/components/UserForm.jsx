import React, { useState, useEffect } from "react";
import { createUser, updateUser } from "../modules/user/UserService";

export default function UserForm({ selectedUser, onClose, onSave }) {
  const [user, setUser] = useState({
    name: "",
    lastName: "",
    email: "",
    phoneNumber: "",
  });

  useEffect(() => {
    if (selectedUser) setUser(selectedUser);
  }, [selectedUser]);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (selectedUser) {
      await updateUser(selectedUser.id, user);
    } else {
      await createUser(user);
    }
    onSave();
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <form
        onSubmit={handleSubmit}
        className="bg-white p-6 rounded shadow-md w-full max-w-md"
      >
        <h2 className="text-xl font-bold mb-4">
          {selectedUser ? "Editar Usuario" : "Crear Usuario"}
        </h2>
        <input
          className="border p-2 w-full mb-2"
          type="text"
          placeholder="Nombre"
          name="name"
          value={user.name}
          onChange={handleChange}
          required
        />
        <input
          className="border p-2 w-full mb-2"
          type="text"
          placeholder="Apellido"
          name="lastName"
          value={user.lastName}
          onChange={handleChange}
          required
        />
        <input
          className="border p-2 w-full mb-2"
          type="email"
          placeholder="Email"
          name="email"
          value={user.email}
          onChange={handleChange}
          required
        />
        <input
          className="border p-2 w-full mb-2"
          type="text"
          placeholder="Teléfono"
          name="phoneNumber"
          value={user.phoneNumber}
          onChange={handleChange}
          required
        />
        <div className="flex justify-end mt-4">
          <button
            type="button"
            onClick={onClose}
            className="mr-2 px-4 py-2 rounded bg-gray-300"
          >
            Cancelar
          </button>
          <button type="submit" className="px-4 py-2 rounded bg-blue-500 text-white">
            Guardar
          </button>
        </div>
      </form>
    </div>
  );
}
