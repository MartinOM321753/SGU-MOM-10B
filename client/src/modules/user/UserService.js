
const API_URL = `http://${import.meta.env.VITE_API_HOST}:${import.meta.env.VITE_API_PORT}${import.meta.env.VITE_API_BASE}`;

export const getUsers = async () => {
  const res = await fetch(`${API_URL}/user/`);
  const data = await res.json();
  return data; 
};

export const getUserById = async (id) => {
  const res = await fetch(`${API_URL}/user/${id}`);
  const data = await res.json();
  return data; 
};

export const createUser = async (user) => {
  const res = await fetch(`${API_URL}/user/`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  const data = await res.json();
  return data;
};

export const updateUser = async (id, user) => {
  const res = await fetch(`${API_URL}/user/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });
  const data = await res.json();
  return data;
};

export const deleteUser = async (id) => {
  const res = await fetch(`${API_URL}/user/${id}`, {
    method: "DELETE",
  });
  return res.ok;
};
